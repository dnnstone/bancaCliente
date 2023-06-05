package proyecto.bootcamp.banca.cliente.utils;


import org.springframework.beans.BeanUtils;
import proyecto.bootcamp.banca.cliente.dto.InputClientDTO;
import proyecto.bootcamp.banca.cliente.model.Client;

import java.util.Random;

public class AppClientUtils {
//    public ClientDto entityToDto
    public static Client dtoToEntity(InputClientDTO inputClientDTO){
        Client client= new Client();
        BeanUtils.copyProperties(inputClientDTO,client,"typeClient");
        return client;


    }
    public static String createNumber()
    {
        Random random = new Random();
        random.nextInt();
        Long number= random.nextLong();
        number = number<0?number*-1:number;

        System.out.println("mumero aleatorio"+number);
        return  correlativoHelper(number.toString());
    }
    public static String correlativoHelper(String flag){
        int aux=flag.length();
        String retorno="";
        if(aux>20){
            retorno= "00002";
        }
        else{
            while (aux<20){
                retorno=(retorno+"0");
                aux++;
            }
            retorno= retorno+flag;
        }
        return retorno;
    }
}
