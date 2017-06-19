
public class Armator implements Druk, Comparable<Armator> {
	private String nazwa;
	private String adres;
	private String email;
	private String telefon;
	private String www;

	public Armator(String n, String a, String e, String t, String w) {
		setNazwa(n);
		setAdres(a);
		setEmail(e);
		setTelefon(t);
		setWWW(w);
	}

	public void setNazwa(String n) {
		nazwa = n;
	}

	public void setAdres(String a) {
		adres = a;
	}

	public void setEmail(String e) {
		email = e;
	}

	public void setTelefon(String t) {
		telefon = t;
	}

	public void setWWW(String w) {
		www = w;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getAdres() {
		return adres;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getWWW() {
		return www;
	}

	@Override
	public int compareTo(Armator o) {
		int a = getNazwa().compareTo(o.getNazwa());

		if (a == 0) {
			return getAdres().compareTo(o.getAdres());
		}
		return a;
	}

	@Override
	public String Wypisz() {
		return ("Armator: " + System.lineSeparator() + "Nazwa: " + getNazwa() + System.lineSeparator() + "Adres: "
				+ getAdres() + System.lineSeparator() + "Email: " + getEmail() + System.lineSeparator() + "Telefon: "
				+ getTelefon() + System.lineSeparator() + "WWW: " + getWWW() + System.lineSeparator()
				+ System.lineSeparator());
	}

}
