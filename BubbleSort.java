public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{56,28,74,35,278,54,19,0};
        int length=array.length;
        int temp=0;
        System.out.print("Unsorted Array is : ");
        for (int Unsortedarray: array){
            System.out.print(Unsortedarray);
            System.out.print(" ");
        }
        System.out.println("");
        System.out.print("Sorted Array is : ");
        for (int i=0;i<length;i++){
            for (int j=0;j<length-1;j++){
                if(array[j]>array[j+1]){
                    temp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        for (int Sortedarray : array) {
            System.out.print(Sortedarray);
            System.out.print(" ");
        }

    }
}
