package muzeum;

public class Dijkstra
{
    private Kopiec min;
    public Sala hisPosition;
    
    public Dijkstra()
    {
        min = new Kopiec();
    }
    
    private void setStartPoint()
    {
        this.hisPosition.CHECK();
        this.hisPosition.LOCK();
        this.hisPosition.ustawNajtanszyKierunek("");
        this.hisPosition.ustawNajtanszaCene(this.hisPosition.getPrice());
        sprawdz();
    }
    public void Przejscie()
    {
        setStartPoint();
        Sala temp;
        while (!min.isEmpty())
        {
            temp = min.get(); 
            NastepnaSala(temp);
        }
    }
    public void CzesciowePrzejscie(Sala destination)
    {
        setStartPoint();
        Sala temp;
        while (!min.isEmpty())
        {
            temp = min.get(); 
            if(temp==destination)
            {
                this.hisPosition=temp;
                break;
            }
            else
            {
                NastepnaSala(temp);
            }
        }
    }
    private void NastepnaSala(Sala temp)
    {
            temp.LOCK();
            this.hisPosition=temp;
            sprawdz();
    }
    private void sprawdz()
    {
        sprawdzN();
        sprawdzNW();
        sprawdzW();
        sprawdzSW();
        sprawdzS();
        sprawdzSE();
        sprawdzE();
        sprawdzNE();
    }
    private void naprawE(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goE()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "E, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
        
        }
    }
    private void naprawN(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goN()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "N, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
        
        }
    }
    private void naprawW(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goW()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "W, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
         
        }
    }
    private void naprawS(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goS()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "S, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
            
        }
    }
    private void naprawNE(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goNE()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "NE, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
            
        }
    }
    private void naprawSE(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goSE()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "SE, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
           
        }
    }
    private void naprawNW(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goNW()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "NW, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
          
        }
    }
    private void naprawSW(Sala temp)
    {
        if(!temp.isLocked() && temp.currentWayPrice() > hisPosition.currentWayPrice() + temp.getPrice() && hisPosition.goSW()==temp)
        {
            temp.ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "SW, ");
            temp.ustawNajtanszaCene(hisPosition.currentWayPrice() + temp.getPrice());
            
        }
    }
    
    
    private void sprawdzN()
    {
        if (hisPosition.goN() != null && !hisPosition.goN().wasChecked() && !hisPosition.goN().isLocked())
        {
            hisPosition.goN().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goN().getPrice());
            hisPosition.goN().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "N, ");
            this.hisPosition.goN().CHECK();
            min.add(hisPosition.goN());
        }
        else
        {
            if (hisPosition.goN() != null && hisPosition.goN().wasChecked() && !hisPosition.goN().isLocked())
            {
                    naprawN(hisPosition.goN());
            }
        }
    }
    private void sprawdzS()
    {
        if (hisPosition.goS() != null && !hisPosition.goS().wasChecked() && !hisPosition.goS().isLocked())
        {
            hisPosition.goS().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goS().getPrice());
            hisPosition.goS().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "S, ");
            this.hisPosition.goS().CHECK();
            min.add(hisPosition.goS());
            
        }
        else
        {
            if (hisPosition.goS() != null && hisPosition.goS().wasChecked() && !hisPosition.goS().isLocked())
            {
                    naprawS(hisPosition.goS());
            }
        }
    }
    private void sprawdzW()
    {
        if (hisPosition.goW() != null && !hisPosition.goW().wasChecked() && !hisPosition.goW().isLocked())
        {
            hisPosition.goW().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goW().getPrice());
            hisPosition.goW().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "W, ");
            this.hisPosition.goW().CHECK();
            min.add(hisPosition.goW());
        }
        else
        {
            if (hisPosition.goW() != null && hisPosition.goW().wasChecked() && !hisPosition.goW().isLocked())
            {
                    naprawW(hisPosition.goW());
            }
        }
    }
    private void sprawdzE()
    {
        if (hisPosition.goE() != null && !hisPosition.goE().wasChecked() && !hisPosition.goE().isLocked())
        {
            hisPosition.goE().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goE().getPrice());
            hisPosition.goE().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "E, ");
            this.hisPosition.goE().CHECK();
            min.add(hisPosition.goE());
        }
        else
        {
            if (hisPosition.goE() != null && hisPosition.goE().wasChecked() && !hisPosition.goE().isLocked())
            {
                    naprawE(hisPosition.goE());
            }
        }
    }
    private void sprawdzNW()
    {
        if (hisPosition.goNW() != null && !hisPosition.goNW().wasChecked() && !hisPosition.goNW().isLocked())
        {
            hisPosition.goNW().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goNW().getPrice());
            hisPosition.goNW().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "NW, ");
            this.hisPosition.goNW().CHECK();
            min.add(hisPosition.goNW());
        }
        else
        {
            if (hisPosition.goNW() != null && hisPosition.goNW().wasChecked() && !hisPosition.goNW().isLocked())
            {
                    naprawNW(hisPosition.goNW());
            }
        }
    }
    private void sprawdzNE()
    {
        if (hisPosition.goNE() != null && !hisPosition.goNE().wasChecked() && !hisPosition.goNE().isLocked())
        {
            hisPosition.goNE().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goNE().getPrice());
            hisPosition.goNE().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "NE, ");
            this.hisPosition.goNE().CHECK();
            min.add(hisPosition.goNE());
        }
        else
        {
            if (hisPosition.goNE() != null && hisPosition.goNE().wasChecked() && !hisPosition.goNE().isLocked())
            {
                    naprawNE(hisPosition.goNE());
            }
        }
    }
    private void sprawdzSW()
    {
        if (hisPosition.goSW() != null && !hisPosition.goSW().wasChecked() && !hisPosition.goSW().isLocked())
        {
            hisPosition.goSW().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goSW().getPrice());
            hisPosition.goSW().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "SW, ");
            this.hisPosition.goSW().CHECK();
            min.add(hisPosition.goSW());
        }
        else
        {
            if (hisPosition.goSW() != null && hisPosition.goSW().wasChecked() && !hisPosition.goSW().isLocked())
            {
                    naprawSW(hisPosition.goSW());
            }
        }
    }
    private void sprawdzSE()
    {
        if (hisPosition.goSE() != null && !hisPosition.goSE().wasChecked() && !hisPosition.goSE().isLocked())
        {
            hisPosition.goSE().ustawNajtanszaCene(hisPosition.currentWayPrice() + hisPosition.goSE().getPrice());
            hisPosition.goSE().ustawNajtanszyKierunek(hisPosition.currentWayDirection() + "SE, ");
            this.hisPosition.goSE().CHECK();
            min.add(hisPosition.goSE());
        }
        else
        {
            if (hisPosition.goSE() != null && hisPosition.goSE().wasChecked() && !hisPosition.goSE().isLocked())
            {
                    naprawSE(hisPosition.goSE());
            }
        }
    }
   
}