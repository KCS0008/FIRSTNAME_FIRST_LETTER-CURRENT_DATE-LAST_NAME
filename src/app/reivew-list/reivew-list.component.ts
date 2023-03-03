import { ReviewService } from './../review.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reivew-list',
  templateUrl: './reivew-list.component.html',
  styleUrls: ['./reivew-list.component.css']
})
export class ReivewListComponent implements OnInit {
  reviews: any[] = [];
  userInput: String = '';
  constructor(private reviewService: ReviewService) { }

  ngOnInit(): void {

  }
  getReview(): void {
    const id = document.getElementById('in') as HTMLInputElement;
    const Id = parseInt(id.value);
    this.reviewService.getReivew(Id).subscribe((review: any) => {
      this.reviews.push(review);
      console.log(this.reviews);
    });
  }

  addReview(): void {
    const review = { review: this.userInput };
    this.reviewService.addReviw(review).subscribe(() => {
      this.reviews.push(review);
      this.userInput = '';
    });
  }
  deleteReview(id: number): void {
    this.reviewService.deleteReview(id).subscribe(() => {
      this.reviews = this.reviews.filter((review: any) =>
        review.id != id);
    })
  }
}
