package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

/**
 * POJO data model class for metrics
 */
@Data
public class Metrics {

  @NonNull
  private Cpu cpu;
  @NonNull
  private Disk disk;
  @NonNull
  private Memory memory;

  @Data
  public static class Cpu {

    @NonNull
    private Load load;

    @Data
    public static class Load {

      @NonNull
      private String frequency;
      @NonNull
      private String stats;
      @NonNull
      private String time;
    }
  }

  public static class Disk {

    private long blockStorageSize;
    @NonNull
    private String blockStoragePath;

    public Disk(long blockStorageSize, String blockStoragePath) {
      this.blockStorageSize = blockStorageSize;
      this.blockStoragePath = blockStoragePath;
    }

    public long getBlockStorageSize() {
      return blockStorageSize;
    }

    public void setBlockStorageSize(long blockStorageSize) {
      this.blockStorageSize = blockStorageSize;
    }

    public String getBlockStoragePath() {
      return blockStoragePath;
    }

    public void setBlockStoragePath(String blockStoragePath) {
      this.blockStoragePath = blockStoragePath;
    }
  }

  @Data
  public static class Memory {

    @NonNull
    private String memory;
    @NonNull
    private String swap;
  }
}
