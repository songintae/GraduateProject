public class Tag
{
  private String tagName;
  private int tagNum;
  
  
  public Tag(String name , int num)
  {
    this.tagName = name;
    this.tagNum = num;
  }
  
  public String getTagName()
  {
    return this.tagName;
  }
  public int getTagNum()
  {
    return this.tagNum;
  }
}
