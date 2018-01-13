package Util;

public class Algebra {
	
	public static void main(String[]args) {
		Coordinates test = new Coordinates(2,2,4);
		test = rotateUV(270,new Coordinates(0,1,0),test);
		
		System.out.println(test.toString());
	}
	
	/*
	 * This method rotates an point in a coordinate system around a unit vector (UV/xyz-axis) and returns a rotated version of this point
	 */
	public static Coordinates rotateUV(double angle, Coordinates axisVector, Coordinates point) {
		
		int cosA = (int) Math.cos(Math.toRadians(angle));
		int sinA = (int) Math.sin(Math.toRadians(angle));
		int axisX = axisVector.getX();
		int axisY = axisVector.getY();
		int axisZ = axisVector.getZ();
		
		int[][] rotationMatrix = {
					{cosA+axisX*axisX*(1-cosA),axisX*axisY*(1-cosA)-axisZ*sinA,axisZ*axisX*(1-cosA)+axisY*sinA},
					{axisY*axisX*(1-cosA)+axisZ*sinA,cosA+axisY*axisY*(1-cosA),axisY*axisZ*(1-cosA)-axisX*sinA},
					{axisZ*axisY*(1-cosA)-axisY*sinA,axisZ*axisY*(1-cosA)+axisX*sinA,cosA+axisZ*axisZ*(1-cosA)}
					};
		for(int x = 0; x < rotationMatrix.length; x++) {
			for(int y = 0; y < rotationMatrix[x].length; y++) {
				System.out.print(rotationMatrix[x][y]+" ");
			}
			System.out.println();
		}
		int[][] rotatedVector = multiplyMatrix(rotationMatrix, point.toVector());
		
		Coordinates rotatedPoint = new Coordinates(rotatedVector[0][0],rotatedVector[1][0],rotatedVector[2][0]);
		
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
