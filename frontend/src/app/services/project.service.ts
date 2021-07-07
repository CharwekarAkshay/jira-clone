import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateProject } from '../types/CreateProject';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  url: string = 'api/projects';

  constructor(private httpClient: HttpClient) { }

  createProject(project: CreateProject): void {
    this.httpClient.post(this.url, project).subscribe((response) => console.log(response));
  }

}
