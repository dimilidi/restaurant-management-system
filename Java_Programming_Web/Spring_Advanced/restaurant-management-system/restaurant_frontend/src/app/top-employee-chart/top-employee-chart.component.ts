import { Component, OnInit } from '@angular/core';
import { BillService } from '../services/bill.service';
import { Chart, registerables } from 'node_modules/chart.js';
import { ReportService } from '../services/report.service';
Chart.register(...registerables);

@Component({
  selector: 'app-top-employee-chart',
  templateUrl: './top-employee-chart.component.html',
  styleUrls: ['./top-employee-chart.component.css']
})
export class TopEmployeeChartComponent implements OnInit {
  chartData: any;
  labelData: any[] = [];
  realData: any[] = [];

  constructor(private reportService:ReportService) {}

  ngOnInit(): void {
    this.fetchTopEmployees();
  }

  fetchTopEmployees() {
    this.reportService.getTopEmployees().subscribe(data => {
      this.chartData = data.data;
      console.log("TOP EMPLOYEE");
      

      console.log(data);
      
    
      if (this.chartData != null) {
        for (let i = 0; i < this.chartData.length; i++) {
          console.log(this.chartData[i]);

          this.labelData.push(this.chartData[i].employeeName);
          this.realData.push(this.chartData[i].billCount);
        }

        this.renderChart(this.labelData, this.realData);
      }

    });
  }

  renderChart(labelData: any, mainData: any) {
    const ctx = document.getElementById('topEmployeesChart');



    new Chart('topEmployeesChart', {
      type: 'doughnut',
      data: {
        labels: labelData,
        datasets: [
          {
            label: '# of Votes',
            data: mainData,
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }
}

