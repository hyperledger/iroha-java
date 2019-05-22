package jp.co.soramitsu.iroha.java.detail;

import java.util.regex.Pattern;

public class Const {

  public static final String assetIdDelimiter = "#";
  public static final String accountIdDelimiter = "@";
  public static final String hostPortDelimiter = ":";
  public static final int accountDetailsMaxLength = 4194304;

  public static final Pattern accountDetailsKeyPattern = Pattern.compile("[A-Za-z0-9_]{1,64}");
  public static final Pattern accountPattern = Pattern.compile("[a-z_0-9]{1,32}");
  public static final Pattern roleNamePattern = Pattern.compile("[a-z_0-9]{1,32}");
  public static final Pattern assetNamePattern = Pattern.compile("[a-z_0-9]{1,32}");

}
