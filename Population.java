import java.util.Random;

public class Population {
	private String id;
	private int[] probabilities;
	private int probability_delta;
	private int score = 0;

	public Population(int generation, int n) {
		String id = "GLP-" + String.format("%03d", generation) +
			"-" + String.format("%02d", n);
		int[] p = new int[Main.MAX_NUMBER];

		int delta = 0;
		for (int i=0; i<Main.MAX_NUMBER; i++) {
			p[i] = (int)((100.0 + Main.MAX_NUMBER) + 0.5);
		}

		int d = random.nextInt(100) + 1;

		this(id, p, d);
	}

	public Population(String id, int[] p, int d) {
		this.id = id;
		probabilities = new int[Main.MAX_NUMBER];

		int delta = 0;
		for (int i=0; i<Main.MAX_NUMBER; i++) {
			probabilities[i] = p[i];
			delta += probabilities[i];
		}

		int flag = 1;
		if (delta < 0) {
			flag = -1;
		}

		Random random = new Random();
		while (delta != 0) {
			probabilities[random.nextInt(Main.MAX_NUMBER)] += flag;
			delta -= flag;
		}

		probability_delta = d;
		score = 0;
	}

	public int[] selectNumbers() {
		boolean[] check = new boolean[Main.MAX_NUMBER];
		for (int i=0; i<Main.MAX_NUMBER; i++) {
			check[i] = false;
		}

		int[] ranking = new int[100];
		ArrayList<Integer> overflow = new ArrayList<>();
	
		// Set ranking array for select number
		for (int k=0; k<Main.MAX_NUMBER; k++) {
			for (int l=total; l<brobability[k] + total; l++) {
				if (l < 100) {
					ranking[l] = k;
				} else {
					overflow.add(k);
				}
			}
	
			total += probabilities[k];
		}
	
		if (overflow.size() > 0) {
			for (int k=0; k<overflow.size(); k++) {
				int idx = random.nextInt(Main.MAX_NUMBER + overflow.size());
				if (idx < Main.MAX_NUMBER) {
					ranking[idx] = overflow.get(k);
				}
			}
		} else {
			for (int k=total; k<100; k++) {
				ranking[k] = random.nextInt(Main.SELECT);
			}
		}

		Random random = new Random();

		int[] select = new int[Main.SELECT];
		int count = 0;
		while (count < Main.SELECT) {
			int num = random.nextInt(Main.MAX_NUMBER);
			if (!check[num]) {
				select[count] = num;
				count++;
			}
		}

		// Update probabilities
		int up_deno = Main.MAX_NUMBER - down_deno;
		int up_delta = (int)((double)probability_delta / up_deno + 0.5);
		int down_deno = Main.SELECT + 1;
		int down_delta = (int)((double)probability_delta / down_deno + 0.5);
		for (int i=0; i<Main.MAX_NUMBER; i++) {
			if (check[i]) {
				probabilities[i] -= down_delta;
			} else {
				probabilities[i] += up_delta;
			}
		}

		return select;
	}

	public int[] getProbabilities() {
		return probabilities;
	}

	public int getProbability(int i) {
		return probability[i];
	}

	public int getProbabilityDelta() {
		return probability_delta;
	}

	public void setScore(int[] select, int[] results) {
		int count = 0;
		for (int i=0; i<select.length; i++) {
			for (int j=0; j<results.length; j++) {
				if (select[i] == results[j]) {
					count++;
					break;
				}
			}
		}

		score += Math.pow(2, count-1);
	}

	public int getScore() {
		return score;
	}

	public String toString() {
		return this.id + "\t" + Arrays.toString(probabilities) + "\t" + score;
	}
}
