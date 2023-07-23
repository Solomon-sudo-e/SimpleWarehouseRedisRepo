package WarehouseInventory.wareHouseInventory;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash
@JsonIgnoreProperties(ignoreUnknown = true)
public class Warehouse {
    @Id
    private Long id;
    private String name, address, location, warehouse_manager_name, typeOfWarehouse;
    @JsonProperty("warehouse_capacity")
    private int wareHouseCapacity;
    @JsonProperty("inventory")
    private int inventory;
    @JsonProperty("trucks_per_hour")
    private int trucksPerHour;
    @JsonProperty("loading_zones")
    private int loadingZones;
    @JsonProperty("rateOfDistribution")
    private double rateOfDistribution;
    @JsonProperty("last_updated")
    private Instant lastUpdated;
    
}
