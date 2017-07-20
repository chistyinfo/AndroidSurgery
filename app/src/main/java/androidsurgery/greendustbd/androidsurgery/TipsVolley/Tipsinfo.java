package androidsurgery.greendustbd.androidsurgery.TipsVolley;

public class Tipsinfo {
	private String title,thumbnailUrl;
	private String summary;
	private String writer;

	public Tipsinfo() {
	}

	public Tipsinfo(String name, String thumbnailUrl, String summary
                ) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.summary = summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}


	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}



}
