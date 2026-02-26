public class Main {
    public static void main(String[] args) throws IllegalThreadStateException, InterruptedException{
        //SingleThread app = new SingleThread(); // create menu object
        //app.createMenu(); // start menu
        Matrix A=new Matrix(10000,10000);
        Matrix B=new Matrix(10000,10000);
        A.createMatrix(2);
        B.createMatrix(2);
        Matrix C=A.multiplyThread(A,B);
        C.display();
    }
}
