import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

// ManagedBean tukee pienen joukun palveluja
@ManagedBean

// Selitys tuoduille objekteille
@ApplicationScoped 
public class HibernateConnection {

	private static final String NULL = null;
	
	/*
	 * @Nina Ranta
	 * 1300381
	 * Karelia-AMK
	 */
	
	@SuppressWarnings("deprecation")
	public static void hibernatePoista() {
		// Alustetaan Hibernate Sessionfactory -luokan avulla
		// configure() -emtodi lukee asetukset hibernate.cfg.xml
		SessionFactory sf;
		sf = new Configuration().configure().buildSessionFactory();
		
		// Luodaan sessio ja aloitetaan transaktio tietojen käsittely
		Session sessio = sf.openSession();
		sessio.beginTransaction();
		
		// Luodaan olio, ja asetetaan sille tietoja
		Kayttajatiedot kayttajat = new Kayttajatiedot();
		
		// Haetaan tietokannasta käyttäjätunnusta ja salasanaa vastaaviin riveihin
		Kayttajatiedot a_kayttaja = (Kayttajatiedot) sessio.createCriteria(Kayttajatiedot.class).
				add(Restrictions.and(Restrictions.eq("kTunnus", kayttajat.getkTunnus()),
						Restrictions.eq("sSana", kayttajat.getsSana()))).uniqueResult();
		
		// Jos salasana ja käyttäjätunnus oikein kirjoitettu --> käyttäjätiedot poistetaan
		if(a_kayttaja.getkTunnus() == kayttajat.getkTunnus()){
			sessio.delete(kayttajat);
			sessio.getTransaction().commit();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void hibernateLuo() {
		// TODO Auto-generated method stub
		
			// Alustetaan Hibernate Sessionfactory -luokan avulla
				// configure() -emtodi lukee asetukset hibernate.cfg.xml
				SessionFactory sf;
				sf = new Configuration().configure().buildSessionFactory();
				
				// Luodaan sessio ja aloitetaan transaktio tietojen käsittely
				Session sessio = sf.openSession();
				sessio.beginTransaction();
				
				// Luodaan olio
				Kayttajatiedot kayttajat = new Kayttajatiedot();
				
				/* Nämä määritellään lomakkeen(luo.xhtml) kautta 
				kayttajat.setkTunnus("");
				kayttajat.setsSana("");
				kayttajat.seteNimi("");
				kayttajat.setsNimi("");
				kayttajat.setIka();
				kayttajat.setsPuoli(""); */
				
				// Tallennetaan olion tiedot tietokantaan
				sessio.save(kayttajat);
				
				// Päätetään transaktio ja tiedot tallentuvat
				sessio.getTransaction().commit();
	}
	
	@SuppressWarnings("deprecation")
	public static void hibernateMuokkaa() {
		// TODO Auto-generated method stub
		
				// Alustetaan Hibernate Sessionfactory -luokan avulla
				// configure() -metodi lukee asetukset hibernate.cfg.xml
				SessionFactory sf;
				sf = new Configuration().configure().buildSessionFactory();
				
				// Luodaan sessio ja aloitetaan transaktio tietojen käsittely
				Session sessio = sf.openSession();
				sessio.beginTransaction();
				
				// Luodaan olio
				Kayttajatiedot kayttajat = new Kayttajatiedot();
				
				// Tallennetaan olion tiedot tietokantaan
				sessio.save(kayttajat);
				
				// Päätetään transaktio ja tiedot tallentuvat
				sessio.getTransaction().commit();
	}
	
	
	public static void main(Kayttajatiedot kayttajat) {
		
	}

}
