import {
  Component,
  EventEmitter,
  Inject,
  LOCALE_ID,
  OnInit,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { LoginComponent } from 'src/app/login/login.component';
import { CategoryService } from 'src/app/services/category.service';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  onAddCategory = new EventEmitter();
  onEditCategory = new EventEmitter();
  categoryForm: any = FormGroup;
  dialogAction: any = 'Add';
  action: any = 'Add';
  responseMessage: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBuilser: FormBuilder,
    private categoryService: CategoryService,
    public dialogRef: MatDialogRef<CategoryComponent>,
    private snackbarService: SnackbarService
  ) {}

  ngOnInit(): void {
    this.categoryForm = this.formBuilser.group({
      name: [null, [Validators.required]],
    });

    if (this.dialogData.action === 'Edit') {
      this.dialogAction = 'Edit';
      this.action = 'Update';
      this.categoryForm.patchValue(this.dialogData.date);
    }

  }


  handleSubmit() {
    if (this.dialogAction === 'Edit') {
      this.edit();
    } else {
      this.add();
    }
  }

  add() {
    var formData = this.categoryForm.value;
    var data = {
      name: formData.name,
    };

    this.categoryService.add(data).subscribe(
      (response: any) => {
        this.dialogRef.close();
        this.onAddCategory.emit();
        this.responseMessage = response?.message;
        this.snackbarService.openSnackBar(this.responseMessage, 'success');
      },
      (error) => {
        console.log(error);
        if (error.status === 400) {
          const errors = error.error;
          console.log(errors);

          Object.keys(errors).forEach((field) => {
            const control = this.categoryForm.get(field);
            if (control) {
              console.log(errors[field]);
              control.setErrors({ serverError: errors[field] });
            } else {
              this.responseMessage = errors.message;
            }
          });
        } else {
          this.responseMessage =
            error.error?.message || GlobalConstants.genericError;
        }
        this.snackbarService.openSnackBar(
          this.responseMessage,
          GlobalConstants.error
        );
      }
    );
  }

  edit() {
    var formData = this.categoryForm.value;
    var data = {
      id: this.dialogData.date.id,
      name: formData.name,
    };

    this.categoryService.update(data).subscribe(
      (response: any) => {
        console.log(response);

        this.dialogRef.close();
        this.onAddCategory.emit();
        this.responseMessage = response?.message;
        this.snackbarService.openSnackBar(this.responseMessage, 'success');
      },
      (error) => {
        console.log(error);
        if (error.status === 400) {
          const errors = error.error;
          console.log(errors);

          Object.keys(errors).forEach((field) => {
            const control = this.categoryForm.get(field);
            if (control) {
              console.log(errors[field]);
              control.setErrors({ serverError: errors[field] });
            } else {
              this.responseMessage = errors.message;
            }
          });
        } else {
          this.responseMessage =
            error.error?.message || GlobalConstants.genericError;
       
        }
        this.snackbarService.openSnackBar(
          this.responseMessage,
          GlobalConstants.error
        );
      }
    );
  }
}
