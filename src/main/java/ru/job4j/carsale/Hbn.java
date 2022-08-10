package ru.job4j.carsale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Hbn {
    public static void main(String[] args) {

    }
    public List<Ad> showAdsLastDay(SessionFactory sf) {
        List<Ad> list = new ArrayList<>();
        Session session = sf.openSession();
        session.beginTransaction();
        list = session.createQuery("select distinct a from Ad a "
                + "join fetch a.car c join fetch c.marks where a.date = CURRENT_DATE()").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
    public List<Ad> showAdsPhoto(SessionFactory sf) {
        List<Ad> list = new ArrayList<>();
        Session session = sf.openSession();
        session.beginTransaction();
        list = session.createQuery("select distinct a from Ad a "
                + "join fetch a.car c join fetch c.marks where c.photo is not null").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public List<Ad> showAdsMark(SessionFactory sf, Mark mark) {
        List<Ad> list = new ArrayList<>();
        Session session = sf.openSession();
        session.beginTransaction();
        list = session.createQuery("select distinct a from Ad a "
                + "join fetch a.car c join fetch c.marks m where m.name = :fMark").setParameter("fMark", mark).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
