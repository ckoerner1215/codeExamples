import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RunModuleComponent } from './run-module.component';

describe('RunModuleComponent', () => {
  let component: RunModuleComponent;
  let fixture: ComponentFixture<RunModuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RunModuleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RunModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
