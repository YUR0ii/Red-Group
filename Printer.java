 import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Printer implements Printable {

	private static final int INCH = 72;

	private Component component;

	public void printComponent(Component component) {
		this.component = component;

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {

			}
		}
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		Dimension imageableArea = new Dimension((int) pf.getWidth() - (2 * INCH), (int) pf.getHeight() - (2 * INCH));
		double widthRatio = imageableArea.getWidth() / component.getWidth();
		double heightRatio = imageableArea.getHeight() / component.getHeight();
		// If the component fits within the imageable area
		if (widthRatio > 1 && heightRatio > 1) {
			g2d.translate(getCenteredX(pf, 1.0), INCH);
		} else {
			double scale = Math.min(widthRatio, heightRatio);
			g2d.translate(getCenteredX(pf, scale), INCH);
			g2d.scale(scale, scale);
		}

		component.printAll(g2d);
		return PAGE_EXISTS;
	}

	private int getCenteredX(PageFormat pf, double scale) {
		return (int) ((pf.getWidth() / 2.0) - (component.getSize().getWidth() * scale / 2.0));
	}
}