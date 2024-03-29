package GIS.graphviewer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class implements graph coloring algorithms. All algorithms output data 
 * that is needed by animation to draw graph with new nodes colors and update algorithms info.
 * @author LUKASZ
 */
public class Coloring
{
     /**
     * Names of possible coloring algorithms
     */
    public enum ALGORITHM {
            /**
             * DSATUR algorithm
             */
            DSATUR,
            /**
             * RLF algorithm
             */
            RLF };

	/**
         * Implements DSATUR graph coloring algorithm. 
         * @param neighbourhoodMatrix A neighbourhood matrix of this graph
         * @return DSATUROutput object with animation data.
	 */
        public static DSATUROutput DSATUR(ArrayList<ArrayList<Integer>> neighbourhoodMatrix) {
            int N = neighbourhoodMatrix.size();
            ArrayList<ArrayList<Integer>> koloryWierzcholkow = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> stopnieWierzcholkow = new ArrayList<Integer>();
            ArrayList<Integer> nasycenieWierzcholkow = new ArrayList<Integer>();
            ArrayList<Integer> koloryJednejIteracji = new ArrayList<Integer>();
            ArrayList<DSATURInfoItem> infos;

            obliczStopnieWierzcholkow(neighbourhoodMatrix, stopnieWierzcholkow, N);		

            
            
            //metoda wyczysc
            koloryWierzcholkow.clear();
            nasycenieWierzcholkow.clear();
            for (int i=0; i<N; i++) 
            {
                    koloryJednejIteracji.add(i,0);				 //zerowe koloryJednejIteracji poczatkowe
                    nasycenieWierzcholkow.add(i,0); //zerowe nasycenie poczatkowe
            }
            koloryWierzcholkow.add(koloryJednejIteracji);	
            
            infos = kolorujDSATUR(N, neighbourhoodMatrix, koloryWierzcholkow,
				stopnieWierzcholkow, nasycenieWierzcholkow);
            
            DSATUROutput dso = new DSATUROutput(koloryWierzcholkow, infos);
            return dso;
        }
        
        private static String koloryToString(ArrayList<ArrayList<Integer>> a) {
            String s = "";
            int counter = 0;
            for(ArrayList<Integer> w : a){
                counter++;
                s += counter + "| ";
                for(Integer i : w) {
                    s += i.toString() + " ";
                }
                s += "\n";
            }
            return s;
        }
        
    private static String macierzToString(ArrayList<ArrayList<Integer>> m) {
            String s = "";
            int counter = 0;
            for(ArrayList<Integer> w : m){
                counter++;
                s += counter + "| ";
                for(Integer i : w) {
                    s += i.toString() + " ";
                }
                s += "\n";
            }
            return s;
        }

	private static void wypisz(int N,
			ArrayList<ArrayList<Integer>> macierzSasiedztwa,
			ArrayList<ArrayList<Integer>> koloryWierzcholkow, 
			ArrayList<Integer> nasycenieWierzcholkow)

	{
		for (int i=0; i<N; i++)
		{
			System.out.println("WierzchoĹ‚ek " +(i+1)+ ":");
//			System.out.println("Stopien wierzczoĹ‚ka " + (i+1) + " to: " + stopnieWierzcholkow.get(i));
			System.out.println("kolor : " + koloryWierzcholkow.get(koloryWierzcholkow.size()-1).get(i));
			System.out.println("Nasycenie : " + nasycenieWierzcholkow.get(i));
			System.out.println();
			
		}
			
		System.out.println("Macierz poĹ‚Ä…czeĹ„: ");
		for (int i=0; i<N; i++)
		{
			for (int j=0; j<N; j++)				
			System.out.print(macierzSasiedztwa.get(i).get(j) + " ");
			System.out.println();
		}
	}


	private static ArrayList<DSATURInfoItem> kolorujDSATUR(int N,
			ArrayList<ArrayList<Integer>> macierzSasiedztwa,
			ArrayList<ArrayList<Integer>> koloryWierzcholkow,
			ArrayList<Integer> stopnieWierzcholkow,
			ArrayList<Integer> nasycenieWierzcholkow)
	{
		int iteracja=0;
		int maxStopien;
		int maxNasycenie;
                ArrayList<DSATURInfoItem> infos = new ArrayList<>();
                ArrayList<Integer> zeros = new ArrayList<>();
                
//		for (int i=0; i<N; i++) //tyle iteracji ile wierzcholkow
		while(koloryWierzcholkow.get(koloryWierzcholkow.size()-1).contains(0))  //jak zostaly wierzcholki bez krawedzi, to pokoloruj je wszystkie w jednym kroku
		{
			ArrayList<Integer>tablicaKolorow = new ArrayList<Integer>(); //generacja tablicy kolorow
                        String saturationNodes = "";
                        String degreeNodes = "";
                        
			for (int z=0; z<10; z++)
				tablicaKolorow.add(z, z);
			maxStopien = 0;
			maxNasycenie = 0;
			if (iteracja >0) 
			{ //skopiuj koloryJednejIteracji z poprzedniej iteracji do biezacej iteracji
				{
					Integer[] itemArray = new Integer[N]; //koloryWierzcholkow.get(iteracja).size()];// albo wpisac N
					Integer[] returnedArray = koloryWierzcholkow.get(iteracja-1).toArray(itemArray);

					ArrayList<Integer> tmp = new ArrayList<Integer>();
					tmp.addAll(Arrays.asList(returnedArray));

					koloryWierzcholkow.add(tmp);
				}							
			}
                        
                        //=============================
                        System.out.println("iteracja " + iteracja);
                        System.out.println("Stopnie:   " + stopnieWierzcholkow.toString());
                        System.out.println("nasycenie: " + nasycenieWierzcholkow.toString());
                        //=============================
			
			ArrayList<Integer> wierzch = new ArrayList<Integer>();
			ArrayList<Integer> wierzchMax = new ArrayList<Integer>();
			for (int k=0; k<N; k++)
			{
                            if (koloryWierzcholkow.get(iteracja).get(k)==0) 				//jesli niepokolorowany
                            {
                                if (maxNasycenie < nasycenieWierzcholkow.get(k))		//wybierz o najwiekszym nasyceniu wierzcholkowym
                                        maxNasycenie = nasycenieWierzcholkow.get(k);
                            }
                            zeros.add(0);
			}
			for (int k=0; k<N; k++)
			{
				if (nasycenieWierzcholkow.get(k)==maxNasycenie)
					{
						wierzch.add(k);
                                                saturationNodes += " " + k + ",";
						if (maxStopien < stopnieWierzcholkow.get(k))			//wybierz o najwiekszym stopniu wierzcholkowym
						maxStopien = stopnieWierzcholkow.get(k);		
					}
			}
			
			for (int k=0; k<wierzch.size(); k++)
			{
				if (stopnieWierzcholkow.get(wierzch.get(k))==maxStopien)
					wierzchMax.add(wierzch.get(k));
			}
			
			for (int j=0; j<N; j++)
			{
				if(stopnieWierzcholkow.get(j)==maxStopien)
                                    degreeNodes += " " + j + ",";

				if ((macierzSasiedztwa.get(wierzchMax.get(wierzchMax.size()-1)).get(j)!=0) &&       	//warunek istnienia krawedzi
					(koloryWierzcholkow.get(iteracja).get(j)!=0))			//warunek ze sasiad ma kolor				
					tablicaKolorow.remove(koloryWierzcholkow.get(iteracja).get(j));	//ten kolor jest niemozliwy - sasiad ma taki
				if (maxStopien != 0) 
					koloryWierzcholkow.get(iteracja).set(wierzchMax.get(wierzchMax.size()-1), tablicaKolorow.get(1));   //defaultowego koloru nie usuwamy, czyli najnizszy
																													//mozliwy kolor to 2gi argument tablicy
				//max stopien to 0 - wezel nie ma krawedzi, jesli taki istnieje to pokoloruj wolnym kolorem (1) 
				if (maxStopien == 0 && koloryWierzcholkow.get(iteracja).contains((int)0)) 
					koloryWierzcholkow.get(iteracja).set(koloryWierzcholkow.get(iteracja).indexOf((int)0),tablicaKolorow.get(1));
			}
				for (int z=0; z<N; z++)
                                { //uzupelnianie nasycenia wierzcholkow
                                    if (iteracja ==0 && macierzSasiedztwa.get(wierzchMax.get(wierzchMax.size()-1)).get(z)>0 && 
                                                    koloryWierzcholkow.get(iteracja).get(wierzchMax.get(wierzchMax.size()-1))>0) 
                                    nasycenieWierzcholkow.set(z, nasycenieWierzcholkow.get(z)+1);
                                    else if (iteracja > 0 && macierzSasiedztwa.get(wierzchMax.get(wierzchMax.size()-1)).get(z)>0 && 
                                                    (koloryWierzcholkow.get(iteracja).get(z)==0))	 
                                    nasycenieWierzcholkow.set(z, nasycenieWierzcholkow.get(z)+1);
                                }
//			}
			System.out.println("kolory:    " + koloryWierzcholkow.get(koloryWierzcholkow.size()-1));
                        int next = wierzchMax.get(wierzchMax.size()-1);
                        DSATURInfoItem item = new DSATURInfoItem(iteracja+1, maxNasycenie, saturationNodes, maxStopien, degreeNodes, next);
			System.out.println(item.toString());
                        infos.add(item);
                        
			stopnieWierzcholkow.set(wierzchMax.get(wierzchMax.size()-1),0);	  //wyzeruj stopnie dla pokolorowanego wierzchoĹ‚ka
			iteracja ++;
		}
                koloryWierzcholkow.add(0, zeros);
                infos.add(new DSATURInfoItem());
                return infos;
	}

    /**
         * Implements RLF graph coloring algorithm. 
         * @param neighbourhoodMatrix A neighbourhood matrix of this graph
         * @return RLFOutput object with animation data.
	 */
    public static RLFOutput RLF(ArrayList<ArrayList<Integer>> neighbourhoodMatrix) {
            int N = neighbourhoodMatrix.size();
            ArrayList<Integer> stopnieWierzcholkow = new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> koloryWierzcholkow = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> wiersz2 = new ArrayList<Integer>();
            ArrayList<RLFInfoItem> RLFinfos;
            
            //metoda wyczysc
            koloryWierzcholkow.clear();
            for (int i=0; i<N; i++) 
            {
                    wiersz2.add(i,0);				 //zerowe koloryJednejIteracji poczatkowe
            }
            koloryWierzcholkow.add(wiersz2);
            
            obliczStopnieWierzcholkow(neighbourhoodMatrix, stopnieWierzcholkow, N);
            RLFinfos = kolorujRLF(N, neighbourhoodMatrix, koloryWierzcholkow, stopnieWierzcholkow);
            System.out.println("kolory:");
            System.out.println(Model.integerMatrixToString(koloryWierzcholkow));
            System.out.println("infos:");
            String info = "";
            for(RLFInfoItem item : RLFinfos){
                info += item.toString() + "\n";
            }
            System.out.println(info);
            
            RLFOutput output = new RLFOutput(koloryWierzcholkow, RLFinfos);
            return output;
        }

	private static ArrayList<RLFInfoItem> kolorujRLF(int N,
			ArrayList<ArrayList<Integer>> macierzSasiedztwa,
			ArrayList<ArrayList<Integer>> koloryWierzcholkow,
			ArrayList<Integer> stopnieWierzcholkow)
	{
		int maxStopien;
		int iteracja=0;
		ArrayList<Integer> zbiorW = new ArrayList<Integer>();
		ArrayList<Integer> zbiorU = new ArrayList<Integer>();
		ArrayList<Integer> zbiorC = new ArrayList<Integer>();
		ArrayList<Integer> zbiorW_U = new ArrayList<Integer>();
                ArrayList<RLFInfoItem> infos = new ArrayList<>();
                ArrayList<ArrayList<Integer>> myColors = new ArrayList<>();
                ArrayList<Integer> zeros = new ArrayList<>();
                ArrayList<Integer> currentColors = new ArrayList<>();
		
		int kolor = 1;
		for (int i=0; i<N; i++)			//przypisz do zbioru W wszystkie wierzcholki
			{
			zbiorW.add(i, i);
                        currentColors.add(0);
                        zeros.add(0);
			}
		
		while (!zbiorW.isEmpty())
		{
                    
		maxStopien = 0;
		int licz=0;
		zbiorC.clear();
		zbiorW_U.clear();
		zbiorU.clear();
		for (int i=0; i<zbiorW.size(); i++)
			zbiorU.add(i, zbiorW.get(i));
		
		
		if (iteracja >0) 
		{
			{
				Integer[] itemArray = new Integer[N]; //koloryWierzcholkow.get(iteracja).size()];// albo wpisac N
				Integer[] returnedArray = koloryWierzcholkow.get(iteracja-1).toArray(itemArray);

				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.addAll(Arrays.asList(returnedArray));

				koloryWierzcholkow.add(tmp);
			}							
		}	
		
			for (int k=0; k<N; k++)
			{
				
			if (koloryWierzcholkow.get(iteracja).get(k)==0) 				//jesli niepokolorowany
			{
			if (maxStopien < stopnieWierzcholkow.get(k))			//wybierz o najwiekszym stopniu wierzcholkowym
				maxStopien = stopnieWierzcholkow.get(k);			
			}
			}
			if (zbiorW.size()>1)
			{
			zbiorC.add(stopnieWierzcholkow.lastIndexOf(maxStopien));	//dodaj do zbioru C najwyzszy wierzcholek
//			zbiorW.remove(stopnieWierzcholkow.lastIndexOf(maxStopien));
			}
			else
				zbiorC.add(zbiorW.get(0));
                                
				while (!zbiorU.isEmpty())
				{
					for (int j=0; j<zbiorC.size(); j++)
					{											
					zbiorU.remove(zbiorC.get(j));				
						for (int i=0; i<N; i++)							
						{
							if (macierzSasiedztwa.get(zbiorC.get(j)).get(i).equals(1))
							zbiorU.remove(new Integer(i));
						}
					}
					licz =0;
					int licz1=0;
				for (licz =0; licz<zbiorW.size(); licz++)
				{
					if (!zbiorU.contains(zbiorW.get(licz)))
						{
						if (zbiorW_U.size()<(zbiorW.size()-zbiorU.size()))						
							zbiorW_U.add(zbiorW.get(licz));
						else 
							{
							zbiorW_U.set(licz1, zbiorW.get(licz));
							licz1++;
							}
						}
				}
				
				System.out.println();
				System.out.println(zbiorC);
				System.out.println(zbiorW);
				System.out.println(zbiorW_U);
				System.out.println(zbiorU);
                                
                                RLFInfoItem item = new RLFInfoItem(infos.size()+1, new ArrayList<>(zbiorW), new ArrayList<>(zbiorU), 
                                        new ArrayList<>(zbiorC), new ArrayList<>(zbiorW_U));
                                infos.add(item);
                                
                                for(Integer node : zbiorC){
                                    currentColors.set(node, kolor);
                                }
                                myColors.add(new ArrayList<>(currentColors));
				int wybrany=0;			
				int maxLicznik=0;
				int indexWybranego=0;
				if (zbiorU.size()>0)
				{	
					for (int i=0; i<zbiorU.size(); i++)
					{
						int licznik=0;
						for (int j=0; j<zbiorW_U.size(); j++)
						{
							if (macierzSasiedztwa.get(zbiorU.get(i)).get(zbiorW_U.get(j)) > 0)
							licznik++;
						}
						if (licznik>maxLicznik)
						{
							maxLicznik=licznik;
							indexWybranego=i;
						}													
					}
					wybrany = zbiorU.get(indexWybranego);
					zbiorC.add(wybrany);			//dodaje do kolorowania wierzchołek z najwieksza liczba polaczen w W\U
//					zbiorW.remove(new Integer(wybrany));
					zbiorU.remove(new Integer(wybrany));					
				        
                                        if(zbiorU.isEmpty()) {
                                            System.out.println();
                                            System.out.println(zbiorC);
                                            System.out.println(zbiorW);
                                            System.out.println(zbiorW_U);
                                            System.out.println(zbiorU);

                                            RLFInfoItem item2 = new RLFInfoItem(infos.size()+1, new ArrayList<>(zbiorW), new ArrayList<>(zbiorU), 
                                                    new ArrayList<>(zbiorC), new ArrayList<>(zbiorW_U));
                                            infos.add(item2);

                                            for(Integer node : zbiorC){
                                                currentColors.set(node, kolor);
                                            }
                                            myColors.add(new ArrayList<>(currentColors));
                                        }
                                }
			
				}
			
				for (int i=0; i<zbiorC.size(); i++)
				{
					koloryWierzcholkow.get(iteracja).set(zbiorC.get(i),kolor);		//dodac wyniki czastkowe
					zbiorW.remove(new Integer(zbiorC.get(i)));				//usun ze zbioru W (pokolorowany)			
					stopnieWierzcholkow.set(zbiorC.get(i),0);	  //wyzeruj stopnie dla pokolorowanego wierzchoĹ‚ka
				}				
				kolor++;
				iteracja = iteracja + 1;
		}
                System.out.println("MyColors");
                System.out.println(Model.integerMatrixToString(myColors));
                koloryWierzcholkow.clear();
                koloryWierzcholkow.add(zeros);
                koloryWierzcholkow.addAll(myColors);
                infos.add(new RLFInfoItem());
                return infos;
	}


	private static void obliczStopnieWierzcholkow(
			ArrayList<ArrayList<Integer>> macierzSasiedztwa,
			ArrayList<Integer> stopnieWierzcholkow, int N)
	{
		for (int i=0; i<N; i++)  //obliczanie stopni
		{
			int suma = 0;
			for (int j=0; j<N; j++)
			{
				suma = suma + macierzSasiedztwa.get(i).get(j);
			}
			stopnieWierzcholkow.add(i, suma);
		}
	}

	private static void generujMacierzSasiedztwa(
			ArrayList<ArrayList<Integer>> macierzSasiedztwa, int N)
	{
		for (int i=0; i<N; i++)  //generacja macierzy grafu zupeĹ‚nego
		{
			ArrayList<Integer> wiersz1 = new ArrayList<Integer>(5);
			for (int j=0; j<N; j++)
				{
					if (i!=j) wiersz1.add(j, 0);
					else if (i==j) wiersz1.add(j,0);
				}						
			macierzSasiedztwa.add(i, wiersz1);
		}
                
		macierzSasiedztwa.get(0).set(1, 1);
		macierzSasiedztwa.get(1).set(0, 1);
		macierzSasiedztwa.get(1).set(2, 1);
		macierzSasiedztwa.get(2).set(1, 1);
		macierzSasiedztwa.get(2).set(3, 1);
		macierzSasiedztwa.get(3).set(2, 1);
		macierzSasiedztwa.get(3).set(4, 1);
		macierzSasiedztwa.get(4).set(3, 1);
		macierzSasiedztwa.get(4).set(0, 1);
		macierzSasiedztwa.get(0).set(4, 1);
		macierzSasiedztwa.get(7).set(6,1);
		macierzSasiedztwa.get(7).set(8,1);
		macierzSasiedztwa.get(7).set(5,1);
		macierzSasiedztwa.get(5).set(7,1);
		macierzSasiedztwa.get(6).set(7,1);
		macierzSasiedztwa.get(8).set(7,1);
	}


}