/* 
    Ad: Süleyman DENİZHAN
    Kullanıcı No: s20030
    Program Tanımı: Java ile Sayısal Loto Tahmini
*/

package btspsayisalloto;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class BtspSayisalLoto {
    
    //sayısal loto sonuclarını ve kullanıcı tahminlerini tutacak diziler
    private static Integer[] sansliSayilar = new Integer[6];
    private static Integer[] kullaniciSayilari = new Integer[6];
    
    public static void main(String[] args) {
        
        boolean tekrar = false;
        
        JOptionPane.showMessageDialog(null, "Sayısal Loto Tahminlerinizi Girer Misiniz ?");
        
        do
        {
            //kullanıcı tahminleri alınıyor...
            for(int i = 0; i <= 5; i++)
            {
                kullaniciSayilari[i] = elin_ugurlu_gelsin();
            }

            //piyango çekilişi yapılıyor...
            cikmaz_demeyin_sansinizi_deneyin();

            //sıralama yapalım
            Arrays.sort(sansliSayilar);
            Arrays.sort(kullaniciSayilari);

            //sonuçlar string ifadeye çevrilip aralarına virgül konuyor...
            String cikanSayilar = array2string(sansliSayilar);
            String strKullaniciTahmini = array2string(kullaniciSayilari);

            //sonuçlar gösteriliyor...
            JOptionPane.showMessageDialog(null, "Çekilişte Çıkan Sayılar : \n "+ cikanSayilar +" \n Sizin Tahminleriniz : \n " + strKullaniciTahmini);
            JOptionPane.showMessageDialog(null, ne_tutturduk_acaba(sansliSayilar, kullaniciSayilari));
            
            int tekrarOynayalimMi = JOptionPane.showConfirmDialog(null, "Tekrar Oynamak İster misiniz?", "Tekrar Oynayalım mı ?", JOptionPane.YES_NO_OPTION);
            
            if (tekrarOynayalimMi == 0)
            {
                tekrar = false;
                array_reset();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "AA Ben Oynamak İstiyordum :( Küstüm :( !");
                tekrar = true;
            }
            
        } while ( tekrar == false );
    }
    
    /**
     * Array Dizinleri Yeniden Oluşturarak Sıfırlama Yapıyoruz.
     */
    private static void array_reset()
    {
        sansliSayilar = new Integer[6];
        kullaniciSayilari = new Integer[6];
    }
    
    /**
     * Başarılı tahminleri ve tahmin adetini get eder...
     * 
     * @param cekilisSonuclari
     * @param kullaniciTahminleri 
     */
    private static String ne_tutturduk_acaba(Integer[] cekilisSonuclari, Integer[] kullaniciTahminleri)
    {
        int basariliTahminSayisi = 0;
        String basariliTahminler = "";
                
        for (int i=0; i <= 5; i++)
        {
            if ( arrayValueExists(kullaniciTahminleri, cekilisSonuclari[i]))
            {
                basariliTahminSayisi++;
                basariliTahminler += cekilisSonuclari[i]+" ";
            }
        }
        
        if ( basariliTahminSayisi == 0 )
        {
            return "Üzgünüm :( Hiç Tutturamadınız..!";
        }
        else
        {
            return "Tebrikler !! "+ basariliTahminSayisi +" Tutturdunuz..! \n Tutturduğunuz Sayılar : \n "+ basariliTahminler;
        }
    }
    
    /**
     * Kullanıcı Top Çekiyor...
     * @return 
     */
    private static int elin_ugurlu_gelsin()
    {
        boolean durum = true;
        int kullaniciTahmini = 1;
        
        do
        {
            String tahmin = JOptionPane.showInputDialog(null, "1-49 Arasında Bir Sayı Giriniz");
            if ( tahmin.isEmpty() )
            {
                JOptionPane.showMessageDialog(null, "Bir Sayı Girmelisiniz..!");
                durum = false;
            }
            else
            {
                int kullaniciTahmin = Integer.parseInt(tahmin);

                if ( kullaniciTahmin > 49 )
                {
                    JOptionPane.showMessageDialog(null, "49'dan Büyük Sayı Giremezsiniz..!");
                    durum = false;
                }
                else if ( kullaniciTahmin < 1)
                {
                    JOptionPane.showMessageDialog(null, "1'den Küçük Sayı Giremezsiniz..!");
                    durum = false;
                }
                else if ( arrayValueExists(kullaniciSayilari, kullaniciTahmin ) == true )
                {
                    JOptionPane.showMessageDialog(null, "Bu Sayıyı Zaten Girmişsiniz..!");
                    durum = false;
                }
                else
                {
                    kullaniciTahmini = kullaniciTahmin;
                    durum = true;
                }
            }
        }
        while ( durum == false);
        
        return kullaniciTahmini;
    }
    
    /**
     * Şanslı sayıları çekiyoruz...
     */
    public static void cikmaz_demeyin_sansinizi_deneyin()
    {
        for ( int i = 0; i<=5; i++)
        {
            boolean durum = false;
            do
            {
                int cekilenSayi = cek_bir_sayi();
            
                if ( arrayValueExists(sansliSayilar, cekilenSayi ) == true )
                {
                    durum = false;
                }
                else
                {
                    sansliSayilar[i] = cekilenSayi;
                    durum = true;
                }
                
            } while(durum == false );
        }
    }
    
    /**
     * Array'ı String'e Yazar...
     * 
     * @param list
     * @return 
     */
    private static String array2string(Integer[] list)
    {
        String str = "";
        
        for ( int i = 0; i < list.length; i++)
        {
            if ( i == (list.length - 1) )
            {
                str += list[i];
            }
            else
            {
                str += list[i] +",";
            }
        }
        
        return str;
    }
    
    /**
     * Rastgele Sayi Oluşturur...
     * @return 
     */
    private static int cek_bir_sayi()
    {
        int rastgeleSayi = (int) ( Math.random() * ( 50 - 1 ) + 1 );
        return rastgeleSayi;
    }
    
    /**
     * Array'da değer var mı yok mu diye kontrol eder..
     * 
     * @param list
     * @param item
     * @return 
     */
    private static boolean arrayValueExists(Integer[] list, Integer item)
    {        
        boolean durum = false;
        
        for( Integer key : list )
        {
            if ( key == item )
            {
                durum = true;
            }
        }
        return durum;
    }
}
