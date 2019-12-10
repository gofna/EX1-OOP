package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

public class Functions_GUI implements functions {
	public LinkedList<function> list = new LinkedList<function>();

	@Override
	public boolean add(function arg0) {
		return this.list.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return this.list.addAll(arg0);
	}

	@Override
	public void clear() {
		this.list.clear();

	}

	@Override
	public boolean contains(Object arg0) {
		return this.list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return this.list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		Iterator<function> iter = this.list.iterator();
		return iter;
	}

	@Override
	public boolean remove(Object arg0) {
		return this.list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.list.retainAll(arg0);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.list.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {
		LinkedList<function> list = new LinkedList<function>();

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str;
			str = br.readLine();
			function f1 = new Polynom("0");
			function f = new ComplexFunction(f1);
			while (str != null) {
				f = f.initFromString(str);
				list.add(f);
				str = br.readLine();
			}
			br.close();
			fr.close();
			System.out.println(list.toString());
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}

	}

	@Override
	public void saveToFile(String file) throws IOException { // need to fix

		try {
			FileWriter fw = new FileWriter(file);
			PrintWriter outs = new PrintWriter(fw);

			Iterator<function> it = this.iterator();
			while (it.hasNext()) {
				outs.println(it.next().toString() + "/n");
			}
			outs.close();
			fw.close();
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {		
		StdDraw.setCanvasSize(width, height);
		double[] x = new double[resolution + 1];
		double[] y = new double[resolution + 1];
		// rescale the coordinate system
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());

		//////// vertical lines
		int diff = 0;
		if (rx.get_max() / 10 < 10) {
			diff = 1;
		} else {
			diff = (int) rx.get_max() / 10;
		}
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (int i = (int) rx.get_min(); i <= rx.get_max(); i = i + diff) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}

		//////// horizontal lines
		int diff2 = 0;
		if ((ry.get_max() - ry.get_min()) / 10 < 10) {
			diff2 = 1;
		} else {
			diff2 = (int) (ry.get_max() - ry.get_min()) / 10;
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i = i + diff2) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}

		//////// x axis
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 12));
		for (int i = (int) rx.get_min(); i <= rx.get_max(); i = i + diff) {
			StdDraw.text(i, 0 - 0.3, Integer.toString((int) (i)));
		}

		//////// y axis
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		for (int i = (int) ry.get_min(); i <= ry.get_max(); i = i + diff2) {
			StdDraw.text(0 - 0.3, i, Integer.toString((int) (i)));
		}

		for (int j = 0; j < list.size(); j++) {
			for (int i = 0; i <= resolution; i++) {
				x[i] = rx.get_min() + i * ((rx.get_max() - rx.get_min()) / resolution);
				y[i] = this.list.get(j).f(x[i]);
			}
			// plot the approximation to the function
			int R = (int) (Math.random() * 256);
			int G = (int) (Math.random() * 256);
			int B = (int) (Math.random() * 256);
			Color randomColor = new Color(R, G, B);
			StdDraw.setPenColor(randomColor);
			for (int i = 0; i < resolution; i++) {
				StdDraw.line(x[i], y[i], x[i + 1], y[i + 1]);
			}
		}
	}

	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		
		try {
			FileReader fr = new FileReader(json_file);
			Params p = gson.fromJson(fr, Params.class);
			Range rx = new Range(p.Range_X[0], p.Range_X[1]);
			Range ry = new Range(p.Range_Y[0], p.Range_Y[1]);
			
			drawFunctions(p.Width,p.Height,rx,ry,p.Resolution);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
