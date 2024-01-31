import { Component, OnInit } from '@angular/core';
import { PostDataService } from 'src/services/post-data.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit{
  completedWorkouts: any[] = [];

  constructor(private postDataService: PostDataService) {}

  ngOnInit() {
    this.postDataService.getCompletedWorkouts().subscribe((data: any) => {
      this.completedWorkouts = data.workouts || [];
    });
  }

  formatDate(input: string): string {
    const inputAsString = String(input);
    // Split the input string by commas and convert each part to a number
    const parts = inputAsString.split(',').map(part => parseInt(part, 10));

    // Create a new Date object using the parts. Note that month is 0-indexed in JavaScript Date, so we subtract 1 from the month.
    const date = new Date(parts[0], parts[1] - 1, parts[2], parts[3], parts[4], parts[5]);

    // Format the date as "YYYY.M.D HH:MM"
    const year = date.getFullYear();
    const month = date.getMonth() + 1; // Adding 1 to adjust for the 0-indexed month
    const day = date.getDate();
    const hour = date.getHours();
    const minute = date.getMinutes();

    // Pad the month, day, hour, and minute with leading zeros if necessary
    const formattedMonth = month < 10 ? `0${month}` : month.toString();
    const formattedDay = day < 10 ? `0${day}` : day.toString();
    const formattedHour = hour < 10 ? `0${hour}` : hour.toString();
    const formattedMinute = minute < 10 ? `0${minute}` : minute.toString();

    // Return the formatted string
    return `${year}.${formattedMonth}.${formattedDay} ${formattedHour}:${formattedMinute}`;
  }
}


