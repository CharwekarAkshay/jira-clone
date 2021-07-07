import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.scss'],
  host: {
    class: 'h-100',
  }
})
export class CreateProjectComponent implements OnInit {
  createProjectFormGroup: FormGroup;

  constructor() {
    this.createProjectFormGroup = new FormGroup({
      projectName: new FormControl(null, [Validators.required]),
      projectKey: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(5),
      ]),
    });
  }

  ngOnInit(): void {}
}
