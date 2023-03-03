import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private URL = 'http://localhost:8080/service/v1/review';

  constructor(private http: HttpClient) { }

  getReivew(id: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/service/v1/review/${id}`);
  }

  addReviw(review: any): Observable<any> {
    return this.http.post<any>(this.URL, review);
  }
  deleteReview(id: number): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/service/v1/review/${id}`);
  }
}
