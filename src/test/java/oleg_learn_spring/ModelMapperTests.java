package oleg_learn_spring;

import com.example.demo.application.car.CarService;
import com.example.demo.domain.entity.Car;
import com.example.demo.presentation.car.dto.queries.CarQuery;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

class MyUser {
    String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}

class MyCar {
    Integer id;
    @Getter
    String model;
    MyUser user;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public MyUser getUser() {
        return user;
    }
    
    public void setUser(MyUser user) {
        this.user = user;
    }
}

class MyCarDTO {
    Integer id;
    String model;
    MyUser user;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public MyUser getUser() {
        return user;
    }
    
    public void setUser(MyUser user) {
        this.user = user;
    }
}

public class ModelMapperTests {

    @Test
    void boo() {
        
        ModelMapper modelMapper = new ModelMapper();
        // modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //
        // TypeMap <MyCar, MyCarDTO> carToCarDTOMapping = modelMapper
        //     .createTypeMap(MyCar.class, MyCarDTO.class)
        //     .addMapping(MyCar::getModel, MyCarDTO::setModel);
        
        MyCar car = new MyCar();
        
        MyUser user = new MyUser();
        user.setName("vasya");
        car.setId(5);
        car.setModel("Subaru");
        car.setUser(user);
        
        MyCarDTO carDTO = modelMapper.map(car, MyCarDTO.class);
        System.out.println("");
        
        CarService cs = new CarService();
        Car car0 = cs.getCar(0);
        CarQuery car0query = modelMapper.map(car0, CarQuery.class);
        System.out.println("");
    }

}






