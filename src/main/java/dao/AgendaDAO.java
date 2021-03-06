package dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import model.Agenda;
import util.HibernateUtil;

public class AgendaDAO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 871298166172188592L;
	private Session session;
	private Criteria criteria;

	//
	// public AgendaDAO() {
	// this.session = HibernateUtil.getSessionFactory().openSession();
	// }
	/**
	 * Persistir objeto agenda no banco
	 *
	 */
	public void save(Agenda agenda) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		session.persist(agenda);
		try {

			t.commit();

		} catch (Exception e) {
			t.rollback();
		}

	}

	/**
	 * Atualizar objeto agenda
	 *
	 */
	public void atualizar(Agenda agenda) {
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction t = session.beginTransaction();
			session.update(agenda);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Encontrar Agenda pelo seu id(agendaPK)
	 *
	 */

	public Agenda findById(int idoferta, Date dataEhora) {
		session = HibernateUtil.getSessionFactory().openSession();

		Agenda agenda = new Agenda();
		String hql = "from Agenda where idoferta = :idoferta and data_hora = :dataEhora";
		Query query = (Query) session.createQuery(hql);
		query.setParameter("idoferta", idoferta);
		query.setParameter("dataEhora", dataEhora);
		agenda = (Agenda) query.uniqueResult();

		return agenda;

	}

	@SuppressWarnings("unchecked")
	public void deleteByOferta(int idoferta) {
		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "from Agenda where idoferta = :idoferta";
		Query query = (Query) session.createQuery(hql);
		query.setParameter("idoferta", idoferta);
		List<Agenda> agendas = query.list();
		for (Agenda agenda : agendas) {

			delete(agenda);

		}

	}

	/**
	 * Listar todas as agenda pelo id da oferta
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<Agenda> listById(int idoferta) {
		List<Agenda> listAgenda = null;

		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "from Agenda where IDOFERTA = :idoferta";
		Query query = (Query) session.createQuery(hql);
		query.setParameter("idoferta", idoferta);
		listAgenda = query.list();

		return listAgenda;
	}

	@SuppressWarnings("unchecked")
	public List<Agenda> listAgendaByIdUsuario(int idusuario) {
		List<Agenda> listAgendaByIdUsuario = null;

		session = HibernateUtil.getSessionFactory().openSession();

		String hql = "from Agenda where IDUSUARIO = :idusuario";
		Query query = (Query) session.createQuery(hql);
		query.setParameter("idusuario", idusuario);
		listAgendaByIdUsuario = query.list();
		return listAgendaByIdUsuario;
	}

	/**
	 * Deletar agenda
	 *
	 */
	public void delete(Agenda agenda) {
		org.hibernate.Transaction t = session.beginTransaction();
		session.delete(agenda);
		try {
			t.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @return the criteria
	 */
	public Criteria getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria
	 *            the criteria to set
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}