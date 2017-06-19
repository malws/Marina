import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

public class Port extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String nazwa, mail, adres, tel;
	private ArrayList<Jacht> jachty = new ArrayList<Jacht>();
	private ArrayList<Armator> armatorzy = new ArrayList<Armator>();
	// private ArrayList<Czarter> czartery = new ArrayList<Czarter>();
	private JComboBox<Jacht> cbJachty, cbJachtyCzarter;
	private JLabel lNaszeJachty, lDodaj, lKomunikat, lCKomunikat, lZdjecie;
	private JButton bDodaj, bEdytuj, bUsun, bdDodaj, bdPlik, bDoPlikuJachty, bDoPlikuArmatorzy, bNowyCzarter, bdCzarter;
	private JDialog dDodaj, dCzarter;
	private JTextField tfNazwa, tfDlugosc, tfSzerokosc, tfWysokosc, tfZanurzenie, tfMasa, tfKoje, tfSilnik, tfZagle,
			tfANazwa, tfAAdres, tfAEmail, tfATelefon, tfAWWW, tfImie, tfNazwisko, tfUlica, tfKodPocztowy, tfMiasto,
			tfEmail, tfTelefon, tfDataOd, tfDataDo, tfSciezka;
	private JTextArea taJacht;
	private JCheckBox zagle, silnik;
	JXDatePicker datePickerOd, datePickerDo;
	private ImageIcon iZdjecie;
	private Jacht j = null, ja = null;
	private boolean ok = true, ed = false;

	public Port(String nazwa) {
		setSize(165, 330);
		setTitle("Port " + nazwa);
		setLayout(null);
		setIconImage(new ImageIcon("anchor.png").getImage());
		setResizable(false);

		lNaszeJachty = new JLabel("Nasze jachty:");
		lNaszeJachty.setBounds(10, 10, 100, 20);
		cbJachty = new JComboBox<Jacht>();
		cbJachty.setBounds(10, 40, 140, 20);
		cbJachty.setSelectedItem(jachty);
		cbJachty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				j = (Jacht) combo.getSelectedItem();
				if (j != null) {
					//setSize(380, 330);
					taJacht.setText(j.Wypisz());
					if (j.getZdjecie() != null) {
						lZdjecie.setIcon(j.getZdjecie());
						setSize(640, 330);
					} else {
						lZdjecie.setIcon(iZdjecie);
						setSize(380, 330);
					}

				} else
				{
					taJacht.setText("");
					setSize(165, 330);
				}				
			}

		});
		bDodaj = new JButton("Dodaj jacht");
		bDodaj.setBounds(10, 70, 140, 20);
		bDodaj.setToolTipText("Kliknij, aby dodaæ nowy jacht");
		bDodaj.addActionListener(this);
		bNowyCzarter = new JButton("Nowy czarter");
		bNowyCzarter.setBounds(10, 100, 140, 30);
		bNowyCzarter.addActionListener(this);

		ImageIcon iObrazek = new ImageIcon("sail_boat.png");
		JLabel lObrazek = new JLabel(iObrazek);
		lObrazek.setBounds(30, 140, 80, 80);

		bDoPlikuJachty = new JButton("Zapisz jachty");
		bDoPlikuJachty.setBounds(10, 230, 140, 20);
		bDoPlikuJachty.setToolTipText("Kliknij, aby zapisaæ dane jachtów w pliku tekstowym");
		bDoPlikuJachty.addActionListener(this);
		bDoPlikuArmatorzy = new JButton("Zapisz armatorów");
		bDoPlikuArmatorzy.setBounds(10, 260, 140, 20);
		bDoPlikuArmatorzy.setToolTipText("Kliknij, aby zapisaæ dane armatorów w pliku tekstowym");
		bDoPlikuArmatorzy.addActionListener(this);
		taJacht = new JTextArea();
		taJacht.setEditable(false);
		JScrollPane spJacht = new JScrollPane(taJacht);
		spJacht.setBounds(160, 10, 200, 241);
		// taJacht.setFont(new Font("Serif", Font.ITALIC, 16));
		// taJacht.setLineWrap(true);
		// taJacht.setWrapStyleWord(true);
		taJacht.setBorder(BorderFactory.createLineBorder(Color.blue));
		iZdjecie = new ImageIcon("main.jpg");
		lZdjecie = new JLabel(iZdjecie);
		lZdjecie.setBounds(380, 10, 240, 270);
		// lZdjecie.setBorder(BorderFactory.createLineBorder(Color.blue));
		bEdytuj = new JButton("Edytuj");
		bEdytuj.setBounds(160, 260, 95, 20);
		bEdytuj.addActionListener(this);
		bUsun = new JButton("Usuñ");
		bUsun.setBounds(264, 260, 95, 20);
		bUsun.addActionListener(this);
		add(lNaszeJachty);
		add(cbJachty);
		add(bDodaj);
		add(bNowyCzarter);
		add(lObrazek);
		add(bDoPlikuJachty);
		add(bDoPlikuArmatorzy);
		add(spJacht);
		add(lZdjecie);
		add(bEdytuj);
		add(bUsun);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		nazwa = args[0];
		mail = args[1];
		adres = args[2];
		tel = args[3];
		Port port = new Port(nazwa);
		// System.out.println("Port " + nazwa + ", " + adres + ", e-mail: " +
		// email + ", tel.: " + telefon);
	}

	public void newYacht(String operacja) {
		// Okienko dodawania nowego jachtu
		dDodaj = new JDialog(this, operacja);
		dDodaj.setSize(600, 390);
		dDodaj.setLocation(150, 50);
		dDodaj.setLayout(null);
		dDodaj.setModal(true);

		// Dane jachtu
		ImageIcon iJacht = new ImageIcon("yacht.png");
		JLabel lJacht = new JLabel("Dane jachtu", iJacht, JLabel.RIGHT);
		lJacht.setBounds(75, 5, 110, 30);
		JLabel lNazwa = new JLabel("Nazwa jachtu:");
		lNazwa.setBounds(10, 50, 100, 20);
		tfNazwa = new JTextField();
		tfNazwa.setBounds(150, 50, 100, 20);
		JLabel lDlugosc = new JLabel("D³ugoœæ ca³kowita:");
		lDlugosc.setBounds(10, 80, 130, 20);
		tfDlugosc = new JTextField();
		tfDlugosc.setBounds(150, 80, 100, 20);
		JLabel lSzerokosc = new JLabel("Szerokoœæ kad³uba:");
		lSzerokosc.setBounds(10, 110, 130, 20);
		tfSzerokosc = new JTextField();
		tfSzerokosc.setBounds(150, 110, 100, 20);
		JLabel lWysokosc = new JLabel("Wysokoœæ kabiny:");
		lWysokosc.setBounds(10, 140, 130, 20);
		tfWysokosc = new JTextField();
		tfWysokosc.setBounds(150, 140, 100, 20);
		JLabel lZanurzenie = new JLabel("Zanurzenie:");
		lZanurzenie.setBounds(10, 170, 130, 20);
		tfZanurzenie = new JTextField();
		tfZanurzenie.setBounds(150, 170, 100, 20);
		JLabel lMasa = new JLabel("Masa ca³kowita:");
		lMasa.setBounds(10, 200, 130, 20);
		tfMasa = new JTextField();
		tfMasa.setBounds(150, 200, 100, 20);
		JLabel lKoje = new JLabel("Liczba koi:");
		lKoje.setBounds(10, 230, 130, 20);
		tfKoje = new JTextField();
		tfKoje.setBounds(150, 230, 100, 20);
		JLabel lSilnik = new JLabel("Silnik:");
		tfSilnik = new JTextField();
		JLabel lZagle = new JLabel("Powierzchnia ¿agli:");
		tfZagle = new JTextField();
		silnik = new JCheckBox("silnik");
		silnik.setBounds(10, 260, 90, 20);
		silnik.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (silnik.isSelected() == true) {
					ok = true;
					lSilnik.setBounds(10, 290, 130, 20);
					tfSilnik.setBounds(10, 310, 100, 20);
					dDodaj.add(lSilnik);
					dDodaj.add(tfSilnik);
					dDodaj.repaint();
				}
				if (silnik.isSelected() == false) {
					dDodaj.remove(lSilnik);
					dDodaj.remove(tfSilnik);
					dDodaj.repaint();
				}
			}
		});
		zagle = new JCheckBox("¿agle");
		zagle.setBounds(140, 260, 90, 20);
		zagle.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (zagle.isSelected() == true) {
					ok = true;
					lZagle.setBounds(140, 290, 130, 20);
					tfZagle.setBounds(140, 310, 100, 20);
					dDodaj.add(lZagle);
					dDodaj.add(tfZagle);
					dDodaj.repaint();
				}
				if (zagle.isSelected() == false) {
					dDodaj.remove(lZagle);
					dDodaj.remove(tfZagle);
					dDodaj.repaint();
				}
			}
		});
		dDodaj.add(lJacht);
		dDodaj.add(lNazwa);
		dDodaj.add(tfNazwa);
		dDodaj.add(lDlugosc);
		dDodaj.add(tfDlugosc);
		dDodaj.add(lSzerokosc);
		dDodaj.add(tfSzerokosc);
		dDodaj.add(lWysokosc);
		dDodaj.add(tfWysokosc);
		dDodaj.add(lZanurzenie);
		dDodaj.add(tfZanurzenie);
		dDodaj.add(lMasa);
		dDodaj.add(tfMasa);
		dDodaj.add(lKoje);
		dDodaj.add(tfKoje);
		dDodaj.add(silnik);
		dDodaj.add(zagle);

		// Dane armatora
		ImageIcon iArmator = new ImageIcon("wheel.png");
		JLabel lArmator = new JLabel("Dane armatora", iArmator, JLabel.RIGHT);
		lArmator.setBounds(350, 5, 150, 30);
		JLabel lANazwa = new JLabel("Nazwa armatora:");
		lANazwa.setBounds(310, 50, 100, 20);
		tfANazwa = new JTextField();
		tfANazwa.setBounds(450, 50, 100, 20);
		JLabel lAAdres = new JLabel("Adres armatora:");
		lAAdres.setBounds(310, 80, 100, 20);
		tfAAdres = new JTextField();
		tfAAdres.setBounds(450, 80, 100, 20);
		JLabel lAEmail = new JLabel("Adres e-mail:");
		lAEmail.setBounds(310, 110, 100, 20);
		tfAEmail = new JTextField();
		tfAEmail.setBounds(450, 110, 100, 20);
		JLabel lATelefon = new JLabel("Telefon:");
		lATelefon.setBounds(310, 140, 100, 20);
		tfATelefon = new JTextField();
		tfATelefon.setBounds(450, 140, 100, 20);
		JLabel lAWWW = new JLabel("WWW:");
		lAWWW.setBounds(310, 170, 100, 20);
		tfAWWW = new JTextField();
		tfAWWW.setBounds(450, 170, 100, 20);

		dDodaj.add(lArmator);
		dDodaj.add(lANazwa);
		dDodaj.add(tfANazwa);
		dDodaj.add(lAAdres);
		dDodaj.add(tfAAdres);
		dDodaj.add(lAEmail);
		dDodaj.add(tfAEmail);
		dDodaj.add(lATelefon);
		dDodaj.add(tfATelefon);
		dDodaj.add(lAWWW);
		dDodaj.add(tfAWWW);

		lKomunikat = new JLabel("");
		lKomunikat.setBounds(310, 230, 250, 20);
		lKomunikat.setFont(new Font("Courier New", Font.BOLD, 12));
		lKomunikat.setForeground(Color.RED);

		JLabel lZdj = new JLabel("Zdjêcie: ");
		lZdj.setBounds(310, 200, 100, 20);
		tfSciezka = new JTextField();
		tfSciezka.setBounds(450, 200, 100, 20);
		JButton bZdjecie = new JButton();
		bZdjecie.setBounds(420, 200, 20, 20);
		bZdjecie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(dDodaj, "Wczytaj", FileDialog.LOAD);
				fd.setVisible(true);
				tfSciezka.setText(fd.getDirectory() + fd.getFile());
			}

		});
		JButton bUsunZdjecie = new JButton();
		bUsunZdjecie.setBounds(560, 205, 10, 10);
		bUsunZdjecie.addActionListener(new ActionListener() {

			@Override
		public void actionPerformed(ActionEvent e) {
					tfSciezka.setText("");
			}

		});

		bdPlik = new JButton("Z pliku");
		bdPlik.setBounds(370, 260, 120, 20);
		bdPlik.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(dDodaj, "Wczytaj", FileDialog.LOAD);
				fd.setVisible(true);
				String sciezka = fd.getDirectory() + fd.getFile();

				File plik = new File(sciezka);
				try {
					Scanner odczyt = new Scanner(plik);
					tfNazwa.setText(odczyt.nextLine());
					tfDlugosc.setText(odczyt.nextLine());
					tfSzerokosc.setText(odczyt.nextLine());
					tfWysokosc.setText(odczyt.nextLine());
					tfZanurzenie.setText(odczyt.nextLine());
					tfMasa.setText(odczyt.nextLine());
					tfKoje.setText(odczyt.nextLine());
					silnik.setSelected(true);
					zagle.setSelected(true);
					tfSilnik.setText(odczyt.nextLine());
					tfZagle.setText(odczyt.nextLine());
					tfANazwa.setText(odczyt.nextLine());
					tfAAdres.setText(odczyt.nextLine());
					tfAEmail.setText(odczyt.nextLine());
					tfATelefon.setText(odczyt.nextLine());
					tfAWWW.setText(odczyt.nextLine());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(new JFrame(), "Nie znaleziono pliku", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});
		bdDodaj = new JButton("Zapisz");
		bdDodaj.setBounds(370, 290, 120, 20);
		bdDodaj.addActionListener(this);
		dDodaj.add(lZdj);
		dDodaj.add(tfSciezka);
		dDodaj.add(bZdjecie);
		dDodaj.add(bUsunZdjecie);
		dDodaj.add(lKomunikat);
		dDodaj.add(bdDodaj);
		dDodaj.add(bdPlik);

		if (ed == true) {
			tfNazwa.setText(j.getNazwa());
			tfDlugosc.setText(Float.toString(j.getDlugoscCalkowita()));
			tfSzerokosc.setText(Float.toString(j.getSzerokoscKadluba()));
			tfWysokosc.setText(Float.toString(j.getWysokoscWnetrza()));
			tfZanurzenie.setText(Float.toString(j.getZanurzenie()));
			tfMasa.setText(Integer.toString(j.getMasaCalkowita()));
			tfKoje.setText(Integer.toString(j.getLiczbaKoi()));
			if ((j.typ == "Motorowy") || (j.typ == "¯aglowo - motorowy")) {
				silnik.setSelected(true);
				tfSilnik.setText(Integer.toString(j.getSilnik()));
			}
			if (j.typ == "¯aglowy" || (j.typ == "¯aglowo - motorowy")) {
				zagle.setSelected(true);
				tfZagle.setText(Integer.toString(j.getPowierzchniaZagli()));
			}
			if (j.getZdjecie() != null)
				tfSciezka.setText((j.getZdjecie()).toString());

			tfANazwa.setText(j.getArmator().getNazwa());
			tfAAdres.setText(j.getArmator().getAdres());
			tfAEmail.setText(j.getArmator().getEmail());
			tfATelefon.setText(j.getArmator().getTelefon());
			tfAWWW.setText(j.getArmator().getWWW());
		}
	}

	public void newCharter() {
		// Okienko dodawania danych czarterowych
		dCzarter = new JDialog(this, "Nowy czarter");
		dCzarter.setSize(250, 450);
		dCzarter.setLocation(150, 50);
		dCzarter.setLayout(null);
		dCzarter.setModal(true);

		// Dane wypo¿yczaj¹cego
		ImageIcon iJacht = new ImageIcon("captain.png");
		JLabel lWypozyczajacy = new JLabel("Dane wypo¿yczaj¹cego", iJacht, JLabel.RIGHT);
		lWypozyczajacy.setBounds(10, 5, 200, 30);
		JLabel lImie = new JLabel("Imiê: ");
		lImie.setBounds(10, 50, 100, 20);
		tfImie = new JTextField();
		tfImie.setBounds(110, 50, 100, 20);
		JLabel lNazwisko = new JLabel("Nazwisko:");
		lNazwisko.setBounds(10, 80, 130, 20);
		tfNazwisko = new JTextField();
		tfNazwisko.setBounds(110, 80, 100, 20);
		JLabel lUlica = new JLabel("Ulica:");
		lUlica.setBounds(10, 110, 130, 20);
		tfUlica = new JTextField();
		tfUlica.setBounds(110, 110, 100, 20);
		JLabel lKodPocztowy = new JLabel("KodPocztowy:");
		lKodPocztowy.setBounds(10, 140, 130, 20);
		tfKodPocztowy = new JTextField();
		tfKodPocztowy.setBounds(110, 140, 100, 20);
		JLabel lMiasto = new JLabel("Miasto:");
		lMiasto.setBounds(10, 170, 130, 20);
		tfMiasto = new JTextField();
		tfMiasto.setBounds(110, 170, 100, 20);
		JLabel lTelefon = new JLabel("Telefon:");
		lTelefon.setBounds(10, 200, 130, 20);
		tfTelefon = new JTextField();
		tfTelefon.setBounds(110, 200, 100, 20);
		JLabel lEmail = new JLabel("E-mail:");
		lEmail.setBounds(10, 230, 130, 20);
		tfEmail = new JTextField();
		tfEmail.setBounds(110, 230, 100, 20);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		JLabel lDataOd = new JLabel("Pocz¹tek:");
		lDataOd.setBounds(10, 260, 130, 20);
		datePickerOd = new JXDatePicker();
		datePickerOd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//label.setText(datePickerOd.getDate().toString());
			}
		});		
		datePickerOd.setBounds(110, 260, 100, 20);
		datePickerOd.setFormats(format);
		datePickerOd.setLinkDay(new Date(),"Dziœ jest {0}");
		
		JLabel lDataDo = new JLabel("Koniec:");
		lDataDo.setBounds(10, 290, 130, 20);
		datePickerDo = new JXDatePicker();
		datePickerDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//label.setText(datePickerOd.getDate().toString());
			}
		});		
		datePickerDo.setBounds(110, 290, 100, 20);
		datePickerDo.setFormats(format);
		datePickerDo.setLinkDay(new Date(),"Dziœ jest {0}");


		
		
		//tfDataOd = new JTextField();
		//tfDataOd.setBounds(110, 260, 100, 20);
		//tfDataOd.setToolTipText("Data w formacie dd-mm-yyyy");
		//tfDataDo = new JTextField();
		//tfDataDo.setBounds(110, 290, 100, 20);
		//tfDataDo.setToolTipText("Data w formacie dd-mm-yyyy");
		JLabel lJachtyCzarter = new JLabel("Jacht:");
		lJachtyCzarter.setBounds(10, 320, 130, 20);
		cbJachtyCzarter = new JComboBox<Jacht>();
		cbJachtyCzarter.setBounds(110, 320, 120, 20);
		for (Jacht j : jachty) {
			cbJachtyCzarter.addItem(j);
		}
		cbJachtyCzarter.setSelectedItem(null);
//		cbJachtyCzarter.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JComboBox combo = (JComboBox) e.getSource();
//				ja = (Jacht) combo.getSelectedItem();
//			}
//
//		});

		lCKomunikat = new JLabel("");
		lCKomunikat.setBounds(10, 350, 200, 20);
		lCKomunikat.setFont(new Font("Courier New", Font.BOLD, 12));
		lCKomunikat.setForeground(Color.RED);

		bdCzarter = new JButton("Zapisz");
		bdCzarter.setBounds(60, 380, 100, 20);
		bdCzarter.addActionListener(this);

		dCzarter.add(lWypozyczajacy);
		dCzarter.add(lImie);
		dCzarter.add(tfImie);
		dCzarter.add(lNazwisko);
		dCzarter.add(tfNazwisko);
		dCzarter.add(lUlica);
		dCzarter.add(tfUlica);
		dCzarter.add(lKodPocztowy);
		dCzarter.add(tfKodPocztowy);
		dCzarter.add(lMiasto);
		dCzarter.add(tfMiasto);
		dCzarter.add(lTelefon);
		dCzarter.add(tfTelefon);
		dCzarter.add(lEmail);
		dCzarter.add(tfEmail);
		dCzarter.add(lDataOd);
		dCzarter.add(datePickerOd);
		//dCzarter.add(tfDataOd);
		dCzarter.add(lDataDo);
		dCzarter.add(datePickerDo);
		//dCzarter.add(tfDataDo);
		dCzarter.add(lJachtyCzarter);
		dCzarter.add(cbJachtyCzarter);
		dCzarter.add(lCKomunikat);
		dCzarter.add(bdCzarter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if (zrodlo == bDodaj) {
			ed = false;
			newYacht("Dodaj jacht");
			dDodaj.setVisible(true);
		}
		if (zrodlo == bEdytuj) {
			if (j != null) {
				ed = true;
				newYacht("Edytuj dane jachtu");
				dDodaj.setVisible(true);
			}
		}
		if (zrodlo == bUsun) {
			if (j != null) {
				Armator armator = j.getArmator();
				jachty.remove(j);
				Collections.sort(jachty);
				setSize(165, 330);
				lZdjecie.setIcon(iZdjecie);
				cbJachty.removeItem(j);

				j = null;
				boolean istnieje = false;
				for (Jacht jacht : jachty) {
					if ((jacht.getArmator()).equals(armator))
						istnieje = true;
				}
				if (istnieje == false) {
					armatorzy.remove(armator);
					Collections.sort(armatorzy);
				}
			}
		}
		if ((zrodlo == bDoPlikuJachty) || (zrodlo == bDoPlikuArmatorzy)) {
			FileDialog sd = new FileDialog(this, "Zapisz", FileDialog.SAVE);
			sd.setVisible(true);
			String katalog = sd.getDirectory();
			File nowyPlik = new File(katalog + sd.getFile());
			try {
				nowyPlik.createNewFile();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(new JFrame(), "Nie uda³o siê utworzyæ pliku.", "B³¹d",
						JOptionPane.ERROR_MESSAGE);
			}
			FileWriter napisz = null;
			try {
				napisz = new FileWriter(nowyPlik, false);
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(new JFrame(), "Nie uda³o siê zapisaæ danych.", "B³¹d",
						JOptionPane.ERROR_MESSAGE);
			}
			PrintWriter zapis = new PrintWriter(napisz);
			if (zrodlo == bDoPlikuJachty) {
				for (Jacht j : jachty) {
					zapis.print(j.Wypisz());
				}
			} else if (zrodlo == bDoPlikuArmatorzy) {
				for (Armator a : armatorzy) {
					zapis.print(a.Wypisz());
				}
			}
			zapis.close();

		}
		if (zrodlo == bdDodaj) {
			ok = true;
			lKomunikat.setText(" ");
			String typ = "";
			String nazwaJachtu = "";
			float dlugosc = 0;
			float szerokosc = 0;
			float wysokosc = 0;
			float zanurzenie = 0;
			int masa = 0;
			int koje = 0;
			int mocSilnika = 0;
			int powierzchniaZagli = 0;
			String nazwaArmatora = "";
			String adresArmatora = "";
			String emailArmatora = "";
			String telefonArmatora = "";
			String wwwArmatora = "";

			if (((tfNazwa.getText()).isEmpty()) || ((tfDlugosc.getText()).isEmpty())
					|| ((tfSzerokosc.getText()).isEmpty() || ((tfWysokosc.getText()).isEmpty()))
					|| ((tfZanurzenie.getText()).isEmpty()) || ((tfMasa.getText()).isEmpty())
					|| ((tfKoje.getText()).isEmpty()) || ((tfANazwa.getText()).isEmpty())
					|| ((tfAAdres.getText()).isEmpty()) || ((tfAEmail.getText()).isEmpty())
					|| ((tfATelefon.getText()).isEmpty()) || ((tfAWWW.getText()).isEmpty())) {
				ok = false;
				lKomunikat.setText("Proszê wype³niæ wszystkie pola.");
			} else {
				nazwaJachtu = tfNazwa.getText();
				try {
					dlugosc = Float.valueOf(tfDlugosc.getText());
					szerokosc = Float.valueOf(tfSzerokosc.getText());
					wysokosc = Float.valueOf(tfWysokosc.getText());
					zanurzenie = Float.valueOf(tfZanurzenie.getText());
					masa = Integer.valueOf(tfMasa.getText());
					koje = Integer.valueOf(tfKoje.getText());
					if (silnik.isSelected() == true) {
						mocSilnika = Integer.valueOf(tfSilnik.getText());
					}
					if (zagle.isSelected() == true) {
						powierzchniaZagli = Integer.valueOf(tfZagle.getText());
					}
					if ((silnik.isSelected() == true) && (zagle.isSelected() == false))
						typ = "Motorowy";
					else if ((silnik.isSelected() == true) && (zagle.isSelected() == true))
						typ = "¯aglowo - motorowy";
					else if ((silnik.isSelected() == false) && (zagle.isSelected() == true))
						typ = "¯aglowy";
					else {
						ok = false;
						lKomunikat.setText("Proszê okreœliæ typ jachtu.");
					}
				} catch (NumberFormatException exi) {
					ok = false;
					lKomunikat.setText("Niepoprawne dane.");
				}

				nazwaArmatora = tfANazwa.getText();
				adresArmatora = tfAAdres.getText();
				emailArmatora = tfAEmail.getText();
				telefonArmatora = tfATelefon.getText();
				wwwArmatora = tfAWWW.getText();
			}

			ImageIcon zdjecie = null;
			if (!((tfSciezka.getText()).isEmpty())) {
				zdjecie = new ImageIcon(tfSciezka.getText());
			}

			if ((ok == true) && (ed == false)) {
				Armator armator = new Armator(nazwaArmatora, adresArmatora, emailArmatora, telefonArmatora,
						wwwArmatora);
				boolean flagaArmator = true;
				for (Armator x : armatorzy) {
					if (((x.getNazwa()).equals(armator.getNazwa())) && ((x.getAdres()).equals(armator.getAdres()))) {
						flagaArmator = false;
						armator = x;
					}
				}
				if (flagaArmator == true) {
					armatorzy.add(armator);
					Collections.sort(armatorzy);
				}

				if (ok == true) {
					Jacht jacht = new Jacht(armator, typ, nazwaJachtu, dlugosc, szerokosc, wysokosc, zanurzenie, masa,
							koje, mocSilnika, powierzchniaZagli, zdjecie);

					jachty.add(jacht);
					Collections.sort(jachty);
					cbJachty.addItem(jacht);
					lKomunikat.setText("Dodano jacht.");
				}
			}
			if ((ok == true) && (ed == true)) {
				jachty.get(jachty.indexOf(j)).setNazwa(nazwaJachtu);
				jachty.get(jachty.indexOf(j)).setDlugoscCalkowita(dlugosc);
				jachty.get(jachty.indexOf(j)).setSzerokoscKadluba(szerokosc);
				jachty.get(jachty.indexOf(j)).setWysokoscWnetrza(wysokosc);
				jachty.get(jachty.indexOf(j)).setZanurzenie(zanurzenie);
				jachty.get(jachty.indexOf(j)).setMasaCalkowita(masa);
				jachty.get(jachty.indexOf(j)).setLiczbaKoi(koje);
				if (!((tfSciezka.getText()).isEmpty())) {
					jachty.get(jachty.indexOf(j)).setZdjecie(new ImageIcon(tfSciezka.getText()));
					cbJachty.setSelectedItem(cbJachty.getSelectedItem());
				}
				else
				{
					jachty.get(jachty.indexOf(j)).setZdjecie(null);
					cbJachty.setSelectedItem(cbJachty.getSelectedItem());
				}

				mocSilnika = 0;
				powierzchniaZagli = 0;
				if (silnik.isSelected() == true) {
					mocSilnika = Integer.valueOf(tfSilnik.getText());
					jachty.get(jachty.indexOf(j)).setSilnik(mocSilnika);
				}
				if (zagle.isSelected() == true) {
					powierzchniaZagli = Integer.valueOf(tfZagle.getText());
					jachty.get(jachty.indexOf(j)).setPowierzchniaZagli(powierzchniaZagli);
				}
				if ((silnik.isSelected() == true) && (zagle.isSelected() == false))
					jachty.get(jachty.indexOf(j)).typ = "Motorowy";
				else if ((silnik.isSelected() == true) && (zagle.isSelected() == true))
					jachty.get(jachty.indexOf(j)).typ = "¯aglowo - motorowy";
				else if ((silnik.isSelected() == false) && (zagle.isSelected() == true))
					jachty.get(jachty.indexOf(j)).typ = "¯aglowy";
				else {
					ok = false;
				}

				jachty.get(jachty.indexOf(j)).getArmator().setNazwa(nazwaArmatora);
				jachty.get(jachty.indexOf(j)).getArmator().setAdres(adresArmatora);
				jachty.get(jachty.indexOf(j)).getArmator().setEmail(emailArmatora);
				jachty.get(jachty.indexOf(j)).getArmator().setTelefon(telefonArmatora);
				jachty.get(jachty.indexOf(j)).getArmator().setWWW(wwwArmatora);

				repaint();
				taJacht.setText(j.Wypisz());
			}

		}

		if (zrodlo == bNowyCzarter) {
			ja = null;
			newCharter();
			dCzarter.setVisible(true);
		}
		if (zrodlo == bdCzarter) {
			ok = true;
			lCKomunikat.setText(" ");
			//Jacht jacht = ja;
			ja = (Jacht) cbJachtyCzarter.getSelectedItem();
			Date dataOd = datePickerOd.getDate();
			Date dataDo = datePickerDo.getDate();
			String imie = "";
			String nazwisko = "";
			String ulica = "";
			String kodPocztowy = "";
			String miasto = "";
			String telefon = "";
			String email = "";
//			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//			try {
//				dataOd = format.parse(datePickerOd.getDate().toString());
//				dataDo = format.parse(datePickerDo.getDate().toString());
//			} catch (ParseException e1) {
//				lCKomunikat.setText("Nieprawid³owy format daty.");
//				ok = false;
//			}

			if (((tfImie.getText()).isEmpty()) || ((tfNazwisko.getText()).isEmpty())
					|| ((tfUlica.getText()).isEmpty() || ((tfKodPocztowy.getText()).isEmpty()))
					|| ((tfMiasto.getText()).isEmpty()) || ((tfTelefon.getText()).isEmpty())
					|| ((tfEmail.getText()).isEmpty()) || (ja == null) || (dataOd == null) || (dataDo == null)) {
				ok = false;
				lCKomunikat.setText("Proszê wype³niæ wszystkie pola.");
			} else {
				imie = tfImie.getText();
				nazwisko = tfNazwisko.getText();
				ulica = tfUlica.getText();
				kodPocztowy = tfKodPocztowy.getText();
				miasto = tfMiasto.getText();
				telefon = tfTelefon.getText();
				email = tfEmail.getText();
			}

			if (ok == true) {
				Czarter czarter = new Czarter(ja, imie, nazwisko, ulica, kodPocztowy, miasto, telefon, email, dataOd,
						dataDo);

				FileDialog sd = new FileDialog(this, "Zapisz", FileDialog.SAVE);
				sd.setVisible(true);
				String katalog = sd.getDirectory();
				File nowyPlik = new File(katalog + sd.getFile());
				try {
					nowyPlik.createNewFile();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Nie uda³o siê utworzyæ pliku.", "B³¹d",
							JOptionPane.ERROR_MESSAGE);
				}
				FileWriter napisz = null;
				try {
					napisz = new FileWriter(nowyPlik, false);
				} catch (IOException e2) {
					JOptionPane.showMessageDialog(new JFrame(), "Nie uda³o siê zapisaæ danych.", "B³¹d",
							JOptionPane.ERROR_MESSAGE);
				}
				PrintWriter zapis = new PrintWriter(napisz);
				zapis.println("Port " + nazwa + ", " + adres + ", tel: " + tel + ", " + mail);
				zapis.print(czarter.Wypisz());

				zapis.close();
				lCKomunikat.setText("Zapisano dane do czarteru.");
			}
		}
	}

}
