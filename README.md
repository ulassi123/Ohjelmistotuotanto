# Ohjelmistotuotanto1
## Yleistä tietoa
Tekijät: Lassi Uosukainen, Markus Martikainen ja Antti Jumpponen

Tiedostot kattavat Ohjelmistotuotanto 1 -projektin, joka on Mökkikylät-yrityksen sisäinen lomaKylä-järjestelmä. Järjestelmässä on tarkoitus hallinnoida Mökkikylät-yrityksen omistamiin mökkeihin liittyviä varauksia, laskuja ja asiakkaita.

![lomaKylän logo](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/parhainlogoikina.png)

## Kuvia järjestelmästä
Kirjautumisikkuna, jossa voi kirjautua sisään joko adminina tai guestina:
![lomaKylän Kirjautumisikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/Kirjautumisikkuna.png)

Alkuikkuna, josta pääsee neljään eri ikkunaan:
![lomaKylän Alkuikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/Aloitusikkuna.png)

Mökki-ikkuna, jossa voidaan hallinnoida mökin tietoja:
![lomaKylän Mökkiikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/M%C3%B6kki-ikkuna.png)

Asiakasikkuna, jossa voidaan hallinnoida asiakkaan tietoja:
![lomaKylän Asiakasikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/Asiakasikkuna.png)

Varausikkuna, jossa voidaan hallinnoida varauksen tietoja:
![lomaKylän Varausikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/Varausikkuna.png)

Laskuikkuna, jossa voidaan hallinnoida laskujen tietoja:
![lomaKylän Laskuikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/Laskuikkuna.png)

Käyttäjäikkuna, jossa voidaan hallinnoida järjestelmän käyttäjien tietoja:
![lomaKylän Kayttakaikkuna](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/K%C3%A4ytt%C3%A4j%C3%A4ikkuna.png)

Ohjelmasta tulostettu pdf-lasku:
![lomaKylän laskupdf](https://github.com/ulassi123/Ohjelmistotuotanto/blob/master/kuvat/laskupdf.png)

## lomaKylä-ohjelmiston asennus ja käyttö:
Lataa projekti zip-tiedostona ja pura se IdeaProjects-kansion alle. Aja zip-tiedostossa oleva lomakyla.sql omassa MySQL:ssäsi. Muokkaa DbConnect-luokkaa siten, että muuttujat DB_URL, DB_NAME ja DB_PASSWORD vastaavat MySQL:ssä olevia tietoja. Arvot voisivat olla esim. "jdbc:mysql://localhost:3306/lomakyla", "root" ja "salasana". Ohjelma hyödyntää IntelliJ:n corretto-23-versiota, jonka pystyt tarvittaessa vaihtamaan projektin kohdalla File -> Project Structure -> SDK.

Ohjelmassa on valmiiksi admin-käyttäjä, johon pystyy kirjautumaan nimellä "nimi" ja salasanalla "salasana". Tällöin voit muokata SQL:n tauluja ja esimerkiksi luomaan tiedoista laskun pdf-tiedostona. Voit myös tarkastella käyttäjiä käyttäjienhallinnasta. Hallintaan pääset kirjautumalla DB_NAME ja DB_PASSWORD -muuttujien alle kirjoitetuilla tunnuksilla.

## Riippuvuudet
-JavaFX
-MySQL
-MySQL connector J

## Tietokanta
Tietokanta löytyy lomakyla.sql nimisestä tiedostosta. Sieltä löytyy seuraavanlaiset taulut:
-laskutiedoille
-asiakkaille
-mökeille
-käyttäjille
-varauksille
