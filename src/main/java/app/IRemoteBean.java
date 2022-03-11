package app;


import domen.City;
import domen.subDomen.SumPopulation;

import javax.ejb.Remote;

@Remote
public interface IRemoteBean {
    public SumPopulation getAllCities(Long id1, Long id2, Long id3);
    public City doGenocide(Long id);
}
