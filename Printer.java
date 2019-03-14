 
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Printer implements Printable {

	public static final int INCH = 72;

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
		g2d.translate(getCenteredX(pf), INCH);
		component.printAll(g);

		return PAGE_EXISTS;
	}

	private int getCenteredX(PageFormat pf) {
		return (int) ((pf.getWidth() / 2) - (component.getSize().getWidth() / 2));
	}

	private int getCenteredY(PageFormat pf) {
		return (int) ((pf.getHeight() / 2) - (component.getSize().getHeight() / 2));
	}

}