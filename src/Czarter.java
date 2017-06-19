import java.text.SimpleDateFormat;
import java.util.Date;

public class Czarter implements Druk {

	private Jacht jacht;
	private String imie;
	private String nazwisko;
	private String ulica;
	private String kodPocztowy;
	private String miasto;
	private String telefon;
	private String email;
	private Date dataOd;
	private Date dataDo;

	public Czarter(Jacht j, String im, String na, String ul, String kp, String mi, String te, String em, Date dod,
			Date ddo) {
		setJacht(j);
		setImie(im);
		setNazwisko(na);
		setUlica(ul);
		setKodPocztowy(kp);
		setMiasto(mi);
		setTelefon(te);
		setEmail(em);
		setDataOd(dod);
		setDataDo(ddo);
	}

	public void setJacht(Jacht j) {
		jacht = j;
	}

	public void setImie(String im) {
		imie = im;
	}

	public void setNazwisko(String na) {
		nazwisko = na;
	}

	public void setUlica(String ul) {
		ulica = ul;
	}

	public void setKodPocztowy(String kp) {
		kodPocztowy = kp;
	}

	public void setMiasto(String mi) {
		miasto = mi;
	}

	public void setTelefon(String te) {
		telefon = te;
	}

	public void setEmail(String em) {
		email = em;
	}

	public void setDataOd(Date dod) {
		dataOd = dod;
	}

	public void setDataDo(Date ddo) {
		dataDo = ddo;
	}

	// ------gettery--------------

	public Jacht getJacht() {
		return jacht;
	}

	public String getImieNazwisko() {
		return (imie + " " + nazwisko);
	}

	public String getAdres() {
		return (ulica + ", " + kodPocztowy + ", " + miasto);
	}

	public String getTelefon() {
		return telefon;
	}

	public String getEmail() {
		return email;
	}

	public String getTermin() {
		SimpleDateFormat outFormat = new SimpleDateFormat("dd.MM.yyyy");
		return (outFormat.format(dataOd) + " - " + outFormat.format(dataDo));
		//return (dataOd + " - " + dataDo);
	}

	@Override
	public String Wypisz() {
		return ("Wypo¿yczaj¹cy:" + System.lineSeparator() + "Imiê i nazwisko: " + getImieNazwisko()
				+ System.lineSeparator() + "Adres: " + getAdres() + System.lineSeparator() + "Telefon: " + getTelefon()
				+ System.lineSeparator() + "Email: " + getEmail() + System.lineSeparator() + "Termin: " + getTermin()
				+ System.lineSeparator() + System.lineSeparator() + "Jacht:" + System.lineSeparator()
				+ getJacht().Wypisz() + System.lineSeparator());

	}
}
