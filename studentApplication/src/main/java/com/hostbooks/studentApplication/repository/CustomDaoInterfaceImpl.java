package com.hostbooks.studentApplication.repository;

import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Repository
public class CustomDaoInterfaceImpl implements CustomDaoInterface {

    @PersistenceContext
    private EntityManager em;


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
        Criteria cr =  em.unwrap(Session.class).createCriteria(Student.class);

        cr.addOrder(Order.desc("studentId"));
        List<Student> students = cr.list();
        return students;
    }

    @Override
    public Integer getNumberStudentByMobileCustom(String mobile) {

        Criteria cr =  em.unwrap(Session.class).createCriteria(Student.class);
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
            CriteriaBuilder cb =  em.getCriteriaBuilder();
            CriteriaQuery<Student> cq =  cb.createQuery(Student.class);
            Root<Student> student = cq.from(Student.class);

            Predicate predicate=  cb.equal(student.get("cellPhone"), mobile);

            cq.where(predicate);
            TypedQuery<Student> typedQuery =  em.createQuery(cq);
            return typedQuery.getResultList();
    }

    @Override
    public List<Course> getAllCourseByPagination(Integer pageNumber, Integer pageSize) {

        CriteriaBuilder cb =  em.getCriteriaBuilder();
        CriteriaQuery<Course> cq =  cb.createQuery(Course.class);
        Root<Course> course = cq.from(Course.class);

        cq.select(course);
        List<Course> res =   em
                .createQuery(cq)
                .setMaxResults(pageNumber)
                .setFirstResult(pageSize)
                .getResultList();

        return res;

    }
}
