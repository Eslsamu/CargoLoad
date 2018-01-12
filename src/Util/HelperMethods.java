package Util;

public class HelperMethods {
	
	public static void main(String[]args) {
	
	}
	/*
	 * 
	 */
	public static Coordinates rotate(double angle, Coordinates axisVector, Coordinates point) {
		int cosA = (int) Math.cos(angle);
		int sinA = (int) Math.sin(angle);
		int axisX = axisVector.getX();
		int axisY = axisVector.getY();
		int axisZ = axisVector.getZ();
		
		int[][] rotationMatrix = {
					{cosA+axisX*axisX*(1-cosA),axisX*axisY*(1-cosA)-axisZ*sinA,axisZ*axisX*(1-cosA)+axisY*sinA},
					{axisY*axisX*(1-cosA)+axisZ*sinA,cosA+axisY*axisY*(1-cosA),axisY*axisZ*(1-cosA)-axisX*sinA},
					{axisZ*axisY*(1-cosA)-axisY*sinA,axisZ*axisY*(1-cosA)+axisX*sinA,cosA+axisZ*axisZ*(1-cosA)}
					};
		
		int[][] rotatedVector = multiplyMatrix(point.toVector(), rotationMatrix);
		Coordinates rotatedPoint = new Coordinates(rotatedVector[0][0],rotatedVector[0][1],rotatedVector[0][2]);
		
		return rotatedPoint;
		
	}
	
	public static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2){
		int[][] matrix = new int[matrix1.length][matrix2[0].length];
		if (matrix1[0].length != matrix2.length){
			System.out.println("The sum is illegal - widths or lengths of matrices don't match!");
		}
		
		for(int matrixRow = 0;matrixRow<matrix1.length;matrixRow++){
			for(int matrixCell1 =0;matrixCell1<matrix1[0].length;matrixCell1++){
				for(int matrixColumn = 0;matrixColumn<matrix2[0].length;matrixColumn++){
					int partialMultiplication = 0;
					for(int matrixCell2 = 0; matrixCell2<matrix2.length;matrixCell2++){
						partialMultiplication += matrix1[matrixRow][matrixCell2]*matrix2[matrixCell2][matrixColumn];					
					}
					matrix[matrixRow][matrixColumn] = partialMultiplication;
				}
			} 
		}
		return matrix;
	}
}
