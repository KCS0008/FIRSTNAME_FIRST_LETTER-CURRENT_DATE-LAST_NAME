import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReivewListComponent } from './reivew-list.component';

describe('ReivewListComponent', () => {
  let component: ReivewListComponent;
  let fixture: ComponentFixture<ReivewListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReivewListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReivewListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
