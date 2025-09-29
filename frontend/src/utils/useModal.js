import { reactive } from 'vue';

export const useModal = reactive({
  modal2Visible: false,
  modalType: null,
});

export function openEditModal(type) {
  useModal.modalType = type;
  useModal.modal2Visible = true;
}
