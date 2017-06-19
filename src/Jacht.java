import javax.swing.ImageIcon;

public class Jacht implements Druk, Comparable<Jacht> {
	private Armator armator;
	public String typ;
	private String nazwa;
	private float dlugoscCalkowita;
	private float szerokoscKadluba;
	private float wysokoscWnetrza;
	private float zanurzenie;
	private int masaCalkowita;
	private int liczbaKoi;
	private int silnik;
	private int powierzchniaZagli;
	private ImageIcon zdjecie = null;

	public Jacht(Armator ar, String ty, String nz, float dc, float sk, float ww, float z, int mc, int lk, int s,
			int pz, ImageIcon zdj) {
		setArmator(ar);
		setTyp(ty);
		setNazwa(nz);
		setDlugoscCalkowita(dc);
		setSzerokoscKadluba(sk);
		setWysokoscWnetrza(ww);
		setZanurzenie(z);
		setMasaCalkowita(mc);
		setLiczbaKoi(lk);
		setSilnik(s);
		setPowierzchniaZagli(pz);
		setZdjecie(zdj);
	}

	public void setArmator(Armator ar) {
		armator = ar;
	}

	public void setTyp(String ty) {
		typ = ty;
	}

	public void setNazwa(String nz) {
		nazwa = nz;
	}

	public void setDlugoscCalkowita(float dc) {
		dlugoscCalkowita = dc;
	}

	public void setSzerokoscKadluba(float sk) {
		szerokoscKadluba = sk;
	}

	public void setWysokoscWnetrza(float ww) {
		wysokoscWnetrza = ww;
	}

	public void setZanurzenie(float z) {
		zanurzenie = z;
	}

	public void setMasaCalkowita(int mc) {
		masaCalkowita = mc;
	}

	public void setLiczbaKoi(int lk) {
		liczbaKoi = lk;
	}

	public void setSilnik(int s) {
		silnik = s;
	}

	public void setPowierzchniaZagli(int pz) {
		powierzchniaZagli = pz;
	}
	
	public void setZdjecie(ImageIcon zdj) {
		zdjecie = zdj;
	}

	// ------gettery--------------

	public Armator getArmator() {
		return armator;
	}

	public String getTyp() {
		return typ;
	}

	public String getNazwa() {
		return nazwa;
	}

	public float getDlugoscCalkowita() {
		return dlugoscCalkowita;
	}

	public float getSzerokoscKadluba() {
		return szerokoscKadluba;
	}

	public float getWysokoscWnetrza() {
		return wysokoscWnetrza;
	}

	public float getZanurzenie() {
		return zanurzenie;
	}

	public int getMasaCalkowita() {
		return masaCalkowita;
	}

	public int getLiczbaKoi() {
		return liczbaKoi;
	}

	public int getSilnik() {
		return silnik;
	}

	public int getPowierzchniaZagli() {
		return powierzchniaZagli;
	}
	
	public ImageIcon getZdjecie() {
		return zdjecie;
	}

	public String toString() {
		return (nazwa);
	}

	@Override
	public String Wypisz() {
		String dane = "Dane jachtu: " + System.lineSeparator() + "Nazwa: " + getNazwa() + System.lineSeparator()
				+ "D³ugoœæ ca³kowita: " + getDlugoscCalkowita() + " m" + System.lineSeparator() + "Szerokoœæ kad³uba: "
				+ getSzerokoscKadluba() + " m" + System.lineSeparator() + "Wysokoœæ kabiny: " + getWysokoscWnetrza()
				+ " m" + System.lineSeparator() + "Zanurzenie: " + getZanurzenie() + " m" + System.lineSeparator()
				+ "Masa ca³kowita: " + getMasaCalkowita() + " kg" + System.lineSeparator() + "Liczba koi: "
				+ getLiczbaKoi() + System.lineSeparator();
		if ((typ == "Motorowy") || (typ == "¯aglowo - motorowy")) {
			dane += "Moc silnika kM: " + getSilnik() + System.lineSeparator();
		}
		if (typ == "¯aglowy" || (typ == "¯aglowo - motorowy")) {
			dane += "Powierzchnia ¿agli: " + getPowierzchniaZagli() + " m2" + System.lineSeparator()
					+ System.lineSeparator();
		}
		dane += armator.Wypisz();
		return (dane);

	}

	@Override
	public int compareTo(Jacht o) {
		int a = getNazwa().compareTo(o.getNazwa());

		if (a == 0) {
			Float dl1 = getDlugoscCalkowita();
			Float dl2 = o.getDlugoscCalkowita();
			return dl1.compareTo(dl2);
		}

		return a;
	}

}
