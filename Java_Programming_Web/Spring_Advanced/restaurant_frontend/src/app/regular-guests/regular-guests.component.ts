import { Component, OnInit } from '@angular/core';
import { BillService } from '../services/bill.service';

@Component({
  selector: 'app-regular-guests',
  templateUrl: './regular-guests.component.html',
  styleUrls: ['./regular-guests.component.css']
})
export class RegularGuestsComponent implements OnInit {
  displayedColumns: string[] = ['name', 'email', 'billCount', 'topProducts'];
  dataSource: any[] = [];

  constructor(private billService: BillService) {}

  ngOnInit() {
    this.billService.getRegularGuests().subscribe(data => {
      this.dataSource = data;
    }, (error: any) => {
      console.error('Error fetching regular guests:', error);
    });
  }


}
