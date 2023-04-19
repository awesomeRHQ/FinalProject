package com.rhq.parkinglotsystem.modle;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_parkinglot
 */
@TableName(value ="t_parkinglot")
@Data
public class ParkingLot implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 停车场编号
     */
    private String plId;

    /**
     * 停车场名称
     */
    private String plName;

    /**
     * 停车场坐标（纬度）
     */
    private Double plLatitude;

    /**
     * 停车场坐标（经度）
     */
    private Double plLongitude;

    /**
     * 听否停满
     */
    private Integer isFull;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ParkingLot other = (ParkingLot) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlId() == null ? other.getPlId() == null : this.getPlId().equals(other.getPlId()))
            && (this.getPlName() == null ? other.getPlName() == null : this.getPlName().equals(other.getPlName()))
            && (this.getPlLatitude() == null ? other.getPlLatitude() == null : this.getPlLatitude().equals(other.getPlLatitude()))
            && (this.getPlLongitude() == null ? other.getPlLongitude() == null : this.getPlLongitude().equals(other.getPlLongitude()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsFull() == null ? other.getIsFull() == null : this.getIsFull().equals(other.getIsFull()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlId() == null) ? 0 : getPlId().hashCode());
        result = prime * result + ((getPlName() == null) ? 0 : getPlName().hashCode());
        result = prime * result + ((getPlLatitude() == null) ? 0 : getPlLatitude().hashCode());
        result = prime * result + ((getPlLongitude() == null) ? 0 : getPlLongitude().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsFull() == null) ? 0 : getIsFull().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", plId=").append(plId);
        sb.append(", plName=").append(plName);
        sb.append(", plLatitude=").append(plLatitude);
        sb.append(", plLongitude=").append(plLongitude);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isFull=").append(isFull);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}