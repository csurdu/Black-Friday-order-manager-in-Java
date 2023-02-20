# Black-Friday-order-manager-in-Java

file:///C:/Users/crist/Downloads/APD___Tema_2___2022.pdf
Cerinta


Pentru aceasta tema trebuie sa implementati un procesator de comenzi de Black Friday in limbajul de programare Java, care sa foloseasca mecanisme de paralelizare.
Ideea temei este acea de a prelucra comenzi in paralel, respectiv de a prelucra si fiecare produs in parte (chiar si din cadrul aceleiasi comenzi) in mod paralel. Puteti face o analogie cu modul in care sunt trimise (sau cel putin erau la un moment dat) produsele din comenzile eMAG: din cauza faptului ca se pot afla in depozite diferite, de multe ori produse din aceeasi comanda pot pleca in colete diferite prin curier pentru eficientizarea livrarii. Scopul temei este acela de a simula acest produs. Astfel, la un moment de timp o comanda poate avea o parte produse dintre produse expediate (shipped), insa doar cand toate produsele din cadrul sau sunt trimise putem spune ca intreaga comanda este shipped.
Programul trebuie sa citeasca doua fisiere de intrare (ce contin comenzi, respectiv produsele continute in cadrul acestora) si sa creeze doua fisiere de iesire in care sa scrie comenzile expediate, respectiv produsele expediate.
Datele de intrare
Fisierele de intrare sunt urmatoarele:

orders.txt cu linii de forma:
id_comanda,nr_produse
unde id comanda este un idcomanda (prefixat cu o).
Reguli fisier orders.txt:
id comanda este un id unic in fisier (nu pot exista id-uri duplicate de comenzi);
nr produse este numarul de produse continute de acea comanda;
o comanda din fisier poate sa contina 0 (zero) produse, caz in care nu va aparea in fisierul de iesire (comenzi de tip Empty Order).
order products.txt cu linii de forma:
id_comanda,id_produs
unde id comanda este id-ul unei comenzi, iar id produs este id-ul unui produs (prefixat cu p),
Reguli fisier order products.txt:
acelasi id de produs se poate regasi de mai multe ori in fisier (in mai multe comenzi, dar si in cadrul aceleiasi comenzi - vezi mai jos);
pot exista linii duplicate, caz in care se considera ca acel produs a fost comandat in cantitate multipla pentru aceeasi comanda (atatea bucati cate linii sunt pentru o anumita comanda);
pot exista linii cu produse al caror id de comanda sa NU existe in fisierul orders.txt; in acest caz se considera ca produsele sunt de tipul Abandoned Cart (au fost adaugate in cos, dar comanda nu a fost trimisa).
Atentie! NU vor exista headere (de tip CSV) in fisierele de intrare!
