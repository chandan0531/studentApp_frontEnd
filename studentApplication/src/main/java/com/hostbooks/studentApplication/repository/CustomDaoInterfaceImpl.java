package com.hostbooks.studentApplication.repository;

import com.hostbooks.studentApplication.dto.CriteriaResponse;
import com.hostbooks.studentApplication.dto.StudentResponse;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class CustomDaoInterfaceImpl implements CustomDaoInterface {

    @PersistenceContext
    private EntityManager em;
    private final CourseDao courseDao;

    public CustomDaoInterfaceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    @Override
    public List<Student> getAllStudentCustom() {

        Criteria criteria = em.unwrap(Session.class).createCriteria(Student.class);
//        crit.add(Restrictions.eq("name", name));
        List<Student> students = criteria.list();

        return students;


    }

    @Override
    public List<Student> getStudentByMobileCustom(String mobile) {
        Criteria criteria = em.unwrap(Session.class).createCriteria(Student.class);

        criteria.add(Restrictions.eq("cellPhone", mobile));

        List<Student> students = criteria.list();
        return students;
    }

    @Override
    public List<Student> getAllStudentCustomDesc() {
        Criteria cr = em.unwrap(Session.class).createCriteria(Student.class);

        cr.addOrder(Order.desc("studentId"));
        List<Student> students = cr.list();
        return students;
    }

    @Override
    public Integer getNumberStudentByMobileCustom(String mobile) {

        Criteria cr = em.unwrap(Session.class).createCriteria(Student.class);
        cr.add(Restrictions.eq("cellPhone", mobile));
        List<Student> students = cr.list();
        return students.size();

//        Criteria cr =  em.unwrap(Session.class).createCriteria(Student.class);
//         Criterion stdId =  Restrictions.gt("studentId", 10);
//
//         Criterion stdName =  Restrictions.like("name", "chandan%");
//
//          LogicalExpression andExp =  Restrictions.and(stdId,stdName);
//
//          cr.add(andExp);
//        List<Student> students = cr.list();
//        return students.size();
    }

    @Override
    public List<Student> getStudentByMobileCriteria(String mobile) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);

        Predicate predicate = cb.equal(student.get("cellPhone"), mobile);

        cq.where(predicate);
        TypedQuery<Student> typedQuery = em.createQuery(cq);
        return typedQuery.getResultList();
    }

    @Override
    public CriteriaResponse getAllCourseByPagination(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> course = cq.from(Course.class);

        if (name != null && !name.isEmpty()) {
            Predicate predicate1 = cb.like(course.get("courseName"), "%" + name + "%");
            cq.where(predicate1);
        }

        if (sortDir != null && !sortDir.isEmpty()) {
            if (sortBy.equals("asc")) {
                cq.orderBy(cb.asc(course.get(sortDir)));
            } else if (sortBy.equals("desc")) {
                cq.orderBy(cb.desc(course.get(sortDir)));
            }
        } else {
            cq.orderBy(cb.desc(course.get("courseId")));
        }

        cq.select(course);
        List<Course> res = em
                .createQuery(cq)
                .setMaxResults(pageSize)
                .setFirstResult(pageNumber)
                .getResultList();

        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.select(cb.count(root));
        Long result = em.createQuery(criteriaQuery).getSingleResult();


        CriteriaResponse criteriaResponse = new CriteriaResponse();
        criteriaResponse.setContent(res);
        criteriaResponse.setTotalPage((result / pageSize) + 1);

        return criteriaResponse;

    }

    @Override
    public CriteriaResponse getFilterCourses(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = cb.createQuery(Course.class);
        Root<Course> courseRoot = criteriaQuery.from(Course.class);

        List<Predicate> predicateList = new ArrayList<>();

        if(!name.equals("")){
            predicateList.add(cb.like(courseRoot.get("courseName"), "%" + name + "%"));
        }

        criteriaQuery.select(courseRoot).where(predicateList.toArray(new Predicate[0]));
        if(sortDir.equalsIgnoreCase("asc")){
            criteriaQuery.orderBy(cb.asc(courseRoot.get(sortBy)));
        }
        if(sortDir.equalsIgnoreCase("desc")){
            criteriaQuery.orderBy(cb.desc(courseRoot.get(sortBy)));
        }

        TypedQuery<Course> typedQuery= em.createQuery(criteriaQuery);
        CriteriaQuery<Long> countLong =  cb.createQuery(Long.class);
        countLong.select(cb.count(countLong.from(Course.class))).where(predicateList.toArray(new Predicate[0]));
        Long count  = em.createQuery(countLong).getSingleResult();

        if(pageNumber.intValue()>0){
            typedQuery.setFirstResult((pageNumber.intValue()-1)*pageSize.intValue());
        }
        typedQuery.setMaxResults(pageSize.intValue());
        CriteriaResponse criteriaResponse = new CriteriaResponse();
        criteriaResponse.setContent(typedQuery.getResultList());
        criteriaResponse.setTotalPage((count/pageSize)+1);
        return criteriaResponse;

    }

    @Override
    public StudentResponse getFilterStudent(String name1, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = cb.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);

        List<Predicate> predicateList = new ArrayList<>();

        if(!name1.equals("")){
            predicateList.add(cb.like(studentRoot.get("name"), "%" + name1 + "%"));
        }

        criteriaQuery.select(studentRoot).where(predicateList.toArray(new Predicate[0]));
        if(sortDir.equalsIgnoreCase("asc")){
            criteriaQuery.orderBy(cb.asc(studentRoot.get(sortBy)));
        }
        if(sortDir.equalsIgnoreCase("desc")){
            criteriaQuery.orderBy(cb.desc(studentRoot.get(sortBy)));
        }

        TypedQuery<Student> typedQuery= em.createQuery(criteriaQuery);
        CriteriaQuery<Long> countLong =  cb.createQuery(Long.class);
        countLong.select(cb.count(countLong.from(Student.class))).where(predicateList.toArray(new Predicate[0]));
        Long count  = em.createQuery(countLong).getSingleResult();

        if(pageNumber.intValue()>0){
            typedQuery.setFirstResult((pageNumber.intValue()-1)*pageSize.intValue());
        }
        typedQuery.setMaxResults(pageSize.intValue());

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(typedQuery.getResultList());
        studentResponse.setTotalPage((count/pageSize)+1);
        return studentResponse;
    }

    //using HQL
    @Override
    public List<Student> getAllStudentHQL() {
//        String hql = "FROM com.hostbooks.studentApplication.entities.Student";
//        String hql = "select s.name from Student s";
//        String hql = "from Student s where s.studentId>10 order by s.name desc";
        String hql1 = "from Student s where s.studentId=:id";
//        String hql1 = "update Student s set s.gender=:gen where s.studentId=:id";
        Session session =  em.unwrap(Session.class);
        Query query = session.createQuery(hql1);
//        query.setParameter("gen", "female");
        query.setParameter("id", 54);

//       int update =  query.executeUpdate();
//       System.out.println(update);
        List<Student> list = query.getResultList();
        return list;
    }

    @Override
    public StudentResponse getStudentPaginationHQL(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        String hql = "";
        if(!sortBy.equals("") && sortBy.equalsIgnoreCase("name")){
            if(sortDir.equalsIgnoreCase("asc"))
            hql = "from Student s order by s.name asc";
            else
                hql = "from Student s order by s.name desc";
        }
        if(!sortBy.equals("") && sortBy.equalsIgnoreCase("cellPhone")){
            if(sortDir.equalsIgnoreCase("asc"))
                hql = "from Student s order by s.cellPhone asc";
            else
                hql = "from Student s order by s.cellPhone desc";
        }
        else {
            hql = "from Student s order by s.studentId asc";
        }

        Session session = em.unwrap(Session.class);

        org.hibernate.query.Query  query = session.createQuery(hql);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);

        List<Student> list = query.list();
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(list);
        studentResponse.setTotalPage((long)2);

        return studentResponse;
    }

    @Override
    public List<Course> getCourseByNameQuery(String name) {

        Session session = em.unwrap(Session.class);
        org.hibernate.query.Query query = session.getNamedQuery("findByCourseName");

        query.setParameter("cname", name);
        List<Course> list =  query.list();
        return list;
    }

    @Override
    public List<Object[]> getDetailsJoin() {

        Session session =  em.unwrap(Session.class);
        String hql = "Select s.name, s.cellPhone, c.courseName, c.courseId from Student as s INNER JOIN s.course as c";
//      String hql = "Select s.name, s.cellPhone, c.courseName, c.courseId from Student as s INNER JOIN s.course as c where s.studentId=54";
        String hql1 = "Select s.name, s.cellPhone, c.courseName, c.courseId from Student as s INNER JOIN s.course as c order by c.courseName";
        org.hibernate.query.Query query = session.createQuery(hql1);

        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public String updateCourseHql() {
        Session session = em.unwrap(Session.class);
//        String hql = "update Course c set c.courseName=:new from Course c INNER JOIN c.student s where s.studentId=54";
        String hql = "update Course c set c.courseName=:new where c.courseId IN (select s.studentId from Student s where s.studentId=54";
        org.hibernate.query.Query query = session.createQuery(hql);
        query.setParameter("new", "java core");
        List<Course> course = query.list();
        return course+"done";
    }


    //    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//    CriteriaQuery<Customer> criteriaQuery = cb.createQuery(Customer.class);
//    Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
//    List<Predicate> predicateList = new ArrayList<>();
//if(!query.getTitle().equalsIgnoreCase("none")
//        && !query.getSearchQuery().equals("")
//  ){
//        predicateList.add(cb.like(customerRoot.get(query.getTitle()),"%"+query.getSearchQuery()+"%"));
//    }
//
//criteriaQuery.select(customerRoot).where(predicateList.toArray(new Predicate[0]));
//if(!query.getSorting().equalsIgnoreCase("none")){
//        criteriaQuery.orderBy(cb.asc(customerRoot.get(query.getSorting())));
//    }

//    TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery);
//
//    //count the customer with query
//    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//countQuery.select(cb.count(countQuery.from(Customer.class)))
//            .where(predicateList.toArray(new Predicate[0]));
//    Long count = entityManager.createQuery(countQuery).getSingleResult();
//
    //if(query.getPageNumber().intValue()>0  ){
//        typedQuery.setFirstResult( (query.getPageNumber().intValue()-1)*query.getPageSize().intValue());
//    }
//
//typedQuery.setMaxResults(query.getPageSize().intValue());
//return  new Page(typedQuery.getResultList(), count.intValue());
}
