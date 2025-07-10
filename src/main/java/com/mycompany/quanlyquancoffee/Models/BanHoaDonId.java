
package com.mycompany.quanlyquancoffee.Models;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
/**
 *
 * @author HELLO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BanHoaDonId implements Serializable {
    private String hoaDon;
    private String ban;
}

