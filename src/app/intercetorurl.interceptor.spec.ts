import { TestBed } from '@angular/core/testing';

import { IntercetorurlInterceptor } from './intercetorurl.interceptor';

describe('IntercetorurlInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      IntercetorurlInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: IntercetorurlInterceptor = TestBed.inject(IntercetorurlInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
