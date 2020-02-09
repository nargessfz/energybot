package energybot.DB;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnergyDAO {

	private static EnergyDAO energyDAO = new EnergyDAO();

	private EnergyDAO() {
	}

	public static EnergyDAO getInstance() {
		return energyDAO;
	}

	public void saveEnergyInfo(EnergyInfo energyInfo) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start a transaction
			transaction = session.beginTransaction();

			// save the energyinfo object
			session.save(energyInfo);

			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateEnergyInfo(EnergyInfo energyInfo) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			Query query = session.createQuery("update EnergyInfo set email_phone = :email" + " where chat_id = :chat");
			query.setParameter("email", energyInfo.getEmail_phone());
			query.setParameter("chat", energyInfo.getChat_id());
			int result = query.executeUpdate();
			// commit transaction
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
}
