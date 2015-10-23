package juz;

public class Good {

	private String name;
	private String description;
	private String price;
	private String href;
	private String images;
	private String sizes;
	private String colors;
	private String colorId;
	private String modeId;

	@Override
	public String toString() {
		return  name + "; " + description + "; " + price + "; " + href
				+ "; " + images + "; " + sizes + "; " + colors + "; " + colorId
				+ "; " + modeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getModeId() {
		return modeId;
	}

	public void setModeId(String modeId) {
		this.modeId = modeId;
	}

	public Good() {
		// TODO Auto-generated constructor stub
	}

}
