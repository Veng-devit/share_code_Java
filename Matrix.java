import java.util.Scanner;
import java.util.Random;
public class Matrix {
    int data[][];
    int rows, cols;
    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new int[rows][cols];
    }
    void createMatrix(int opt) {
        Scanner input = new Scanner(System.in);
        Random rand= new Random();
        if (opt==1){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print("Enter value of matrix[" + i + "][" + j + "]: ");
                    data[i][j] = input.nextInt();
                }
            }
            display();
        }
        else if(opt==2){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i][j] = rand.nextInt(10);
                }
            }
            System.out.println("Matrix generate successfully!");
            display();
        }
    }
    void display(){
        System.out.println("Display matrix:");
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }
    }
    Matrix multiply(Matrix A, Matrix B){
        System.out.println("Multiple Matrix use single thread");
        if(A.cols!=B.rows){
            System.out.println("Can not multiple matrix!");
            System.out.println("Column of matrix A not equal Row of matrix B!");
            return null;
        }
        Matrix C=new Matrix(A.rows, B.cols);
        for(int i=0; i<A.rows;i++){
            for (int j=0;j<B.cols;j++){
                int sum=0;
                for (int k=0; k<A.cols;k++){
                    sum+=A.data[i][k]*B.data[k][j];
                }
                C.data[i][j]=sum;
            }
        }
        return C;
    }
    Matrix multiplyThread(Matrix A, Matrix B) throws InterruptedException{
        System.out.println("Multiple Matrix use multi thread");
        if(A.cols!=B.rows){
            System.out.println("Can not multiple matrix!");
            System.out.println("Column of matrix A not equal Row of matrix B!");
            return null;
        }
        Matrix C=new Matrix(A.rows, B.cols);
        MultipleRow[] multiRow=new MultipleRow[A.rows];
        for(int i=0;i<A.rows;i++){
            multiRow[i]=new MultipleRow(A, B, C, i);
            multiRow[i].start();
        }
        for(int i=0;i<A.rows;i++){
            multiRow[i].join();
        }
        return C;
    }
    
}
class MultipleRow extends Thread{
    Matrix A;
    Matrix B;
    Matrix C;
    int row;
    MultipleRow(Matrix A, Matrix B, Matrix C, int row){
        this.A=A;
        this.B=B;
        this.C=C;
        this.row=row;
    }
    @Override
    public void run() {
        for(int i=0;i<A.cols;i++){
            int sum=0;
            for(int j=0;j<B.cols;j++){
                sum+=A.data[row][j] * B.data[j][i];
            }
            C.data[row][i]=sum;
        }

    }
}
