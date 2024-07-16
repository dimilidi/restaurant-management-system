import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'node_modules/chart.js';
import { ProductService } from '../services/product.service';
Chart.register(...registerables);

@Component({
  selector: 'app-best-seller-chart',
  templateUrl: './best-seller-chart.component.html',
  styleUrls: ['./best-seller-chart.component.css'],
})
export class BestSellerChartComponent implements OnInit {
  constructor(private productService: ProductService) {}

  chartData: any;
  labelData: any[] = [];
  realData: any[] = [];


  ngOnInit(): void {
    this.productService.getBestSellers().subscribe((response) => {
      this.chartData = response;
      console.log(response);
      

      if (this.chartData != null) {
        for (let i = 0; i < this.chartData.length; i++) {
          console.log(this.chartData[i]);

          this.labelData.push(this.chartData[i].name);
          this.realData.push(this.chartData[i].sales);
        }

        this.renderChart(this.labelData, this.realData);
      }
    });
  }

  renderChart(labelData: any, mainData: any) {
    const ctx = document.getElementById('myChart');

    new Chart('myChart', {
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
