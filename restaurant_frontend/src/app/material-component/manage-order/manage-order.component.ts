import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { saveAs } from 'file-saver';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BillService } from 'src/app/services/bill.service';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-manage-order',
  templateUrl: './manage-order.component.html',
  styleUrls: ['./manage-order.component.css'],
})
export class ManageOrderComponent implements OnInit {
  displayedColumns: string[] = [
    'name',
    'category',
    'price',
    'quantity',
    'total',
    'edit',
  ];
  dataSource: any = [];
  manageOrderForm: any = FormGroup;
  categories: any = [];
  products: any = [];
  price: any;
  totalAmount: number = 0;
  responseMessage: any;
  editMode: boolean[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private snackbarService: SnackbarService,
    private billService: BillService,
    private ngxService: NgxUiLoaderService
  ) {}

  ngOnInit(): void {
    this.ngxService.start();
    this.getCategories();

    this.manageOrderForm = this.formBuilder.group({
      name: [
        null,
        [Validators.required, Validators.pattern(GlobalConstants.nameRegex)],
      ],
      email: [
        null,
        [Validators.required, Validators.pattern(GlobalConstants.emailRegex)],
      ],
      contactNumber: [
        null,
        [
          Validators.required,
          Validators.pattern(GlobalConstants.contactNumberRegex),
        ],
      ],
      paymentMethod: [null, [Validators.required]],
      product: [null, [Validators.required]],
      category: [null, [Validators.required]],
      quantity: [1, [Validators.required]],
      price: [null, [Validators.required]],
      total: [0, [Validators.required]],
    });

    this.manageOrderForm.controls['quantity'].valueChanges.subscribe(() => {
      this.calculateTotal();
    });
  }

  getCategories() {
    this.categoryService.getCategoriesWithActiveProducts().subscribe(
      (response: any) => {
        this.ngxService.stop();
        this.categories = response.data;
        console.log(response);
        
      },
      (error: any) => {
        this.handleError(error);
      }
    );
  }

  getProductsByCategory(value: any) {
    this.productService.getProductsByCategory(value.id).subscribe(
      (response: any) => {
        this.products = response.data;
        this.manageOrderForm.controls['price'].setValue('');
        this.manageOrderForm.controls['quantity'].setValue(1);
        this.manageOrderForm.controls['total'].setValue(0);
      },
      (error: any) => {
        this.handleError(error);
      }
    );
  }

  getProductDetails(value: any) {
    this.productService.getProductsById(value.id).subscribe(
      (response: any) => {
        this.price = response.data.price;
        this.manageOrderForm.controls['price'].setValue(this.price);
        this.calculateTotal();
      },
      (error: any) => {
        this.handleError(error);
      }
    );
  }

  calculateTotal() {
    const quantity = this.manageOrderForm.controls['quantity'].value;
    const price = this.manageOrderForm.controls['price'].value;
    if (quantity > 0 && price > 0) {
      const total = quantity * price;
      this.manageOrderForm.controls['total'].setValue(total);
    }
  }

  validateProductAdd() {
    var total = this.manageOrderForm.controls['total'].value;
    var quantity = this.manageOrderForm.controls['quantity'].value;
    return total <= 0 || total === null || quantity <= 0;
  }

  validateSubmit() {
    return (
      this.totalAmount <= 0 ||
      this.manageOrderForm.controls['name'].value === null ||
      this.manageOrderForm.controls['contactNumber'].value === null ||
      this.manageOrderForm.controls['paymentMethod'].value === null
    );
  }

  add() {
    const formData = this.manageOrderForm.value;
    const productExists = this.dataSource.find(
      (e: { id: number }) => e.id === formData.product.id
    );

    if (!productExists) {
      this.totalAmount += formData.total;
      this.dataSource.push({
        id: formData.product.id,
        name: formData.product.name,
        category: formData.category.name,
        quantity: formData.quantity,
        price: formData.price,
        total: formData.total,
      });
      this.dataSource = [...this.dataSource];
      this.snackbarService.openSnackBar(
        GlobalConstants.productAdded,
        'success'
      );

      // Clear product fields
      this.manageOrderForm.controls['product'].setValue(null);
      this.manageOrderForm.controls['category'].setValue(null);
      this.manageOrderForm.controls['price'].setValue(null);
      this.manageOrderForm.controls['quantity'].setValue(1);
      this.manageOrderForm.controls['total'].setValue(0);

      // Reset validation state to prevent immediate display of errors
      this.manageOrderForm.controls['product'].markAsPristine();
      this.manageOrderForm.controls['product'].markAsUntouched();
      this.manageOrderForm.controls['category'].markAsPristine();
      this.manageOrderForm.controls['category'].markAsUntouched();
    } else {
      this.snackbarService.openSnackBar(
        GlobalConstants.productExistError,
        GlobalConstants.error
      );
    }
  }

  updateQuantity(element: any, newQuantity: number) {
    const oldTotal = element.total;
    const newTotal = element.price * newQuantity;
    element.quantity = newQuantity;
    element.total = newTotal;
    this.totalAmount = this.totalAmount - oldTotal + newTotal;
    this.dataSource = [...this.dataSource];
  }

  onQuantityChange(event: any, element: any) {
    const newQuantity = event.target.value;
    if (newQuantity && newQuantity > 0) {
      this.updateQuantity(element, newQuantity);
    } else {
      event.target.value = element.quantity;
    }
  }

  setQuantity(event: any) {
    const quantity = event.target.value;
    if (quantity > 0) {
      this.calculateTotal();
    } else {
      this.manageOrderForm.controls['quantity'].setValue(1);
    }
  }

  saveChanges(index: number) {
    this.editMode[index] = false;
  }

  handleDeleteAction(index: number, element: any) {
    this.totalAmount = this.totalAmount - element.total;
    this.dataSource.splice(index, 1);
    this.dataSource = [...this.dataSource];
  }

  submitAction() {
    const formData = this.manageOrderForm.value;
    const data = {
      name: formData.name,
      email: formData.email,
      contactNumber: formData.contactNumber,
      paymentMethod: formData.paymentMethod,
      total: this.totalAmount.toString(),
      productDetails: JSON.stringify(this.dataSource),
    };

    this.ngxService.start();
    this.billService.generateReport(data).subscribe(
      (response: any) => {
        console.log(response);
        
        this.downloadFile(response.uuid);
        this.manageOrderForm.reset();
        this.dataSource = [];
        this.totalAmount = 0;
      },
      (error: any) => {
        this.handleError(error);
      }
    );
  }

  downloadFile(fileName: string) {
    const data = { uuid: fileName };
    this.billService.getPdf(data).subscribe(
      (response: any) => {
        console.log(response);
        
        saveAs(response, `${fileName}.pdf`);
        this.ngxService.stop();
      },
      (error: any) => {
        console.log(error.error);
      }
    );
  }

  handleError(error: any) {
    console.log(error);
    if (error.error?.message) {
      this.responseMessage = error.error.message;
    } else {
      this.responseMessage = GlobalConstants.genericError;
    }
    this.snackbarService.openSnackBar(
      this.responseMessage,
      GlobalConstants.error
    );
  }

  toggleEditMode(index: number) {
    this.editMode[index] = !this.editMode[index];
  }
}
