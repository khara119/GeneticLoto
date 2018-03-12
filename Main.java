import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static final int GENETICS = 100;
	public static final int POPULATION = 10;
	public static final int MAX_NUMBER = 31;
	public static final int SELECT = 5;

	public static void main(String[] args) {
		public static int[] ranking = new int[100];
		for (int i=0; i<30; i++) {
			ranking[i] = 0;
		}
		for (int i=30; i<50; i++) {
			ranking[i] = 1;
		}
		for (int i=50; i<65; i++) {
			ranking[i] = 2;
		}
		for (int i=65; i<75; i++) {
			ranking[i] = 3;
		}
		for (int i=75; i<83; i++) {
			ranking[i] = 4;
		}
		for (int i=83; i<89; i++) {
			ranking[i] = 5;
		}
		for (int i=89; i<94; i++) {
			ranking[i] = 6;
		}
		for (int i=94; i<97; i++) {
			ranking[i] = 7;
		}
		for (int i=97; i<99; i++) {
			ranking[i] = 8;
		}
		for (int i=99; i<100; i++) {
			ranking[i] = 9;
		}

		Scanner s = new Scanner(System.in);
		ArrayList<int[]> results = new ArrayList<>();
		ArrayList<Integer> bornus_results = new ArrayList<>();

		// Save results
		while((String date = s.next()) != null) {
			int[] numbers = new int[];
			for (int i=0; i<Main.SELECT; i++) {
				numbers[i] = Integer.parseInt(s.next());
			}

			results.add(numbers);
			bornus_results.add(Integer.parseInt(s.next()));
		}

		Random random = new Random();

		// Set init Populations
		Population[] populations = new Population[Main.POPLATION];
		for (int i=0; i<Main.POPULATION; i++) {
			population[i] = new Population(1, 0);
		}

		// Loop generitic
		for (int i=0; i<Main.GENETICS; i++) {
			for (int a=0; a<results.size(); a++) {
				int[] numbers = results.get(a);

				System.out.println("Population ID\tProbabilities\tScore");
				for (int j=0; j<Main.POPULATION; j++) {
					System.out.println(populations[j]);
				}

				// Loop each population
				for (int j=0; j<Main.POPULATION; i++) {
					int[] select = populations[j].selectNumbers();
					System.out.println(populations[j].getId() + "\t" + Arrays.toString(select));

					populations[j].setScore(select, numbers);
				}

				System.out.println("Result:\t" + Arrays.toString(numbers));
			}

			Population nextPopulations = new Population[Main.Population];
			Arrays.sort(populations, (a, b) -> a.getScore() > b.getScore());

			nextPopulations[0] = populations[0];
			nextPopulations[1] = populations[1];

			for (int j=2; j<Main.Population; j++) {
				Population na = populations[ranking[random.nextInt(100)]];
				Population nb = populations[ranking[random.nextInt(100)]];

				
			}
		}
	}
}
