import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
//	let greet = HttpGet.getWeather()

	var body: some View {
        Text(viewModel.phrases)
	}
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var phrases: String = "Loading..."

        init() {
            Task {
                do {
                    let result = try await HttpGet().getWeather(location: "上海")
                    DispatchQueue.main.async {
                        self.phrases = result
                    }
                } catch {
                    DispatchQueue.main.async {
                        self.phrases = error.localizedDescription
                    }
                }
            }
        }
    }
}
