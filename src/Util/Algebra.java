package Util;
/**
 * Used for calculating rotation and mirroring of pentominoes
 */
public class Algebra {
	/*
	 * rotates an point in a coordinate system around a unit vector (UV/xyz-axis) and returns a rotated version of this point
	 * @param 
	 */
	public static Coordinates rotateUV(double angle, Axis a, Coordinates point) {
		
		Coordinates axis = a.toUnitVector();
		
		int cosA = (int) Math.cos(Math.toRadians(angle));
		int sinA = (int) Math.sin(Math.toRadians(angle));
		
		int[][] rotationMatrix = {
					{cosA+axis.x*axis.x*(1-cosA),axis.x*axis.y*(1-cosA)-axis.z*sinA,axis.z*axis.x*(1-cosA)+axis.y*sinA},
					{axis.y*axis.x*(1-cosA)+axis.z*sinA,cosA+axis.y*axis.y*(1-cosA),axis.y*axis.z*(1-cosA)-axis.x*sinA},
					{axis.z*axis.y*(1-cosA)-axis.y*sinA,axis.z*axis.y*(1-cosA)+axis.x*sinA,cosA+axis.z*axis.z*(1-cosA)}
					};
		
		int[][] rotatedVector = multiplyMatrix(rotationMatrix, point.toVector());
		
		return new Coordinates(rotatedVector[0][0],rotatedVector[1][0],rotatedVector[2][0]);
	}
	
	/*
	 * reflects a vector on a plane of two coordinate axis
	 * @param a the axis that is left out XY Plane --> a=z
	 */
	public static Coordinates reflect(Axis a, Coordinates point) {
		int temp = 0;
		switch(a) {
		case X: temp = 0; break;
		case Y: temp = 1; break;
		case Z: temp = 2; break;
		}
		int[][] reflectionMatrix = {{1,0,0},{0,1,0},{0,0,1}};
		reflectionMatrix[temp][temp]=-1;
		int[][] reflectedVector = multiplyMatrix(reflectionMatrix, point.toVector());
	
		return new Coordinates(reflectedVector[0][0],reflectedVector[1][0],reflectedVector[2][0]);
	}
	/**
         * Multiplies two 2D matrixes
         * @param matrix1
         * @param matrix2
         * @return the result as a 2D matrix
         */
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
