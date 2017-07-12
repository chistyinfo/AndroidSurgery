package androidsurgery.greendustbd.androidsurgery.TechInfoVolley;

public class Teachinfo {
	private String title,thumbnailUrl;
	private String publisher;
	private String writer;

	public Teachinfo() {
	}

	public Teachinfo(String name, String thumbnailUrl, String publisher,
                     String writer) {
		this.title = name;
		this.thumbnailUrl = thumbnailUrl;
		this.publisher = publisher;
		this.writer = writer;
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


	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

}
