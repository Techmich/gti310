package gti310.tp4;

public class Convertisseur {

	float[][][] imageConvertie;
	int[] infoImage;
	double[][] imageDCT;

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

	public double[][] convertirDCT(float[][] imageConvertie){

	double[][] image = new double[8][8];

		for(int u = 0; u < 8; u++){

			for(int v = 0; v < 8; v++){
				
				double sum = 0.0;

				for(int i = 0; i < 8; i++){

					for(int j = 0; j < 8; j++){

						sum+= Math.cos(((2*i+1)/(16))*u*Math.PI)*Math.cos(((2*j+1)/(16))*v*Math.PI)*imageConvertie[i][j];

					}

				}
				if (u == 0 && v == 0)
				sum*=(((1/Math.sqrt(2.0))*(1/Math.sqrt(2.0)))/4.0);
				if (u > 0 && v == 0)
					sum*=((1*(1/Math.sqrt(2.0)))/4.0);
				if (u == 0 && v > 0)
					sum*=(((1/Math.sqrt(2.0))*1)/4.0);
				if (u > 0 && v > 0)
					sum*=((1*1)/4.0);
				image[u][v] = sum ;
				
			}
		}





				return image;
			}

		}

