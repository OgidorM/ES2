package com.es2.objectpool;

import java.net.HttpURLConnection;

public class Main {

    public static void main(String[] args) {

        try {
            ReusablePool pool = ReusablePool.getInstance();
            HttpURLConnection conn = pool.acquire();

            System.out.println("Conexão adquirida.");
            System.out.println("Response Code: " + conn.getResponseCode());

            pool.release(conn);

            System.out.println("Conexão liberada.");

        } catch (PoolExhaustedException e) {
            System.out.println("Pool está cheia.");
        } catch (ObjectNotFoundException e) {
            System.out.println("Tentativa de liberar conexão inválida.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}