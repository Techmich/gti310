package gti310.tp4;

public class Convertisseur {

	float[][][] imageConvertie;
	int[] infoImage;
	int[][] imageDCT;

	public Convertisseur(int[][][] image){

		imageConvertie = convertirBMP(image);
		this.infoImage = infosImage(image);
		this.imageDCT= convertirDCT(imageConvertie);

	}

	public int[] infosImage(int[][][] image){

		int hauteur,largeur;
		hauteur = image[0].length;
		largeur = image[0][0].length;

		int[] infoImage = new int[2];
		infoImage[0] = hauteur;
		infoImage[1] = largeur;

		return infoImage;
	}

	public float[][][] convertirBMP(int[][][] imageAConvertir){
		int hauteurImage,largeurImage,valeur;

		hauteurImage = (infosImage(imageAConvertir))[0];
		largeurImage = (infosImage(imageAConvertir))[1];
		valeur = 0;

		float[][][] imageConvertie = new float[3][hauteurImage][largeurImage];

		for(int h = 0; h < hauteurImage; h++){

			for(int l = 0; l < largeurImage; l++){

				//System.out.println(h + " Hauteur");
				//System.out.println(l + " Largeur");


				if (valeur==0) {
					imageConvertie[valeur][h][l] = ((float)((0.299*imageAConvertir[0][h][l])+(0.587*imageAConvertir[1][h][l])+(0.114*imageAConvertir[2][h][l])));
					//System.out.println(imageConvertie[valeur][h][l]);
					//System.out.println(valeur + " Valeur");
					valeur++;
				}

				if (valeur==1) {
					imageConvertie[valeur][h][l] = ((float)((-0.1687*imageAConvertir[0][h][l])+(-0.3313*imageAConvertir[1][h][l])+(0.5*imageAConvertir[2][h][l])+128));
					//System.out.println(imageConvertie[valeur][h][l]);
					//System.out.println(valeur + " Valeur");
					valeur++;
				}

				if (valeur==2) {
					imageConvertie[valeur][h][l] = ((float)((0.5*imageAConvertir[0][h][l])+(-0.4187*imageAConvertir[1][h][l])+(-0.0813*imageAConvertir[2][h][l])+128));
					//System.out.println(imageConvertie[valeur][h][l]);
					//System.out.println(valeur + " Valeur");
					valeur = 0;
				}



			}
		}
		return imageConvertie;

	}

	public int[][] convertirDCT(float[][][] imageConvertie){

		int[][] imageY = new int[infoImage[0]][infoImage[1]];
		int[][] imageDCT = new int[infoImage[0]][infoImage[1]];

		for(int h = 0; h < imageY.length; h++){

			for(int l = 0; l < imageY[0].length; l++){

				imageY[h][l] = (int) imageConvertie[0][h][l];
				//System.out.println(imageY[h][l]);

			}

		}

		

		for(int a = 0; a < 8; a++){

			for(int b = 0; b < 8; b++){
				
				double sum = 0.0;

				for(int m = 0; m < 8; m++){

					for(int n = 0; n < 8; n++){

						sum+=Math.cos(((2*m+1)/(2.0*8))*a*Math.PI)*Math.cos(((2*n+1)/(2.0*8))*b*Math.PI)*imageDCT[m][n];

						imageDCT[m][n] = imageY[m][n];
						System.out.println(imageY[m][n]);

					}

				}

			}
		}





				return imageDCT;
			}

		}

