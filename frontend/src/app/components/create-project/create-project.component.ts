import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectService } from 'src/app/services/project.service';
import { CreateProject } from 'src/app/types/CreateProject';

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

  constructor(private projectService: ProjectService) {
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

  createProject(): void {
    const values = this.createProjectFormGroup.value;
    const createProject: CreateProject = {
      projectKey: values['projectKey'],
      projectName: values['projectName'],
    }

    this.projectService.createProject(createProject);
  }
}
